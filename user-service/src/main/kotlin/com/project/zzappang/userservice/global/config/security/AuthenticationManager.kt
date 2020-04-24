package com.project.zzappang.userservice.global.config.security


import com.project.zzappang.userservice.global.config.jwt.JwtTokenProvider
import io.jsonwebtoken.Claims
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.ReactiveAuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import java.util.stream.Collectors



@Component
class AuthenticationManager(
        private val ACCESS_TOKEN_VALIDITY_SECONDS : Long = 5*60*60,
        private val SIGNING_KEY : String = "hello",
        private val AUTHORITIES_KEY : String = "world",
        @Autowired
        private val tokenProvider: JwtTokenProvider
) : ReactiveAuthenticationManager {

    override fun authenticate(authentication: Authentication): Mono<Authentication> {
        val authToken = authentication.credentials.toString()
        val username: String?
        username = try {
            tokenProvider!!.getUsernameFromToken(authToken)
        } catch (e: Exception) {
            null
        }
        return if (username != null && !tokenProvider?.isTokenExpired(authToken)!!) {
            val claims: Claims = tokenProvider.getAllClaimsFromToken(authToken)
            val roles: List<String>? = claims.get<List<*>>(AUTHORITIES_KEY, List::class.java) as List<String>?
            val authorities = roles!!.stream().map { role: String? -> SimpleGrantedAuthority(role) }.collect(Collectors.toList())
            val auth = UsernamePasswordAuthenticationToken(username, username, authorities)
            SecurityContextHolder.getContext().authentication = AuthenticatedUser(username,authorities,true)
            Mono.just(auth)
        } else {
            Mono.empty()
        }
    }
}
