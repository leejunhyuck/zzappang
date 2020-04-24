package com.project.zzappang.userservice.global.config.security

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.core.context.SecurityContextImpl
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Component
class SecurityContextRepository : ServerSecurityContextRepository {
    @Autowired
    private val authenticationManager: AuthenticationManager? = null
    override fun save(swe: ServerWebExchange, sc: SecurityContext): Mono<Void> {
        throw UnsupportedOperationException("Not supported yet.")
    }

    override fun load(swe: ServerWebExchange): Mono<SecurityContext> {
        val request = swe.request
        val authHeader = request.headers.getFirst(HttpHeaders.AUTHORIZATION)
        var authToken: String? = null
        if (authHeader != null && authHeader.startsWith(TOKEN_PREFIX)) {
            authToken = authHeader.replace(TOKEN_PREFIX, "")
        } else {
            logger.warn("couldn't find bearer string, will ignore the header.")
        }
        return if (authToken != null) {
            val auth: Authentication = UsernamePasswordAuthenticationToken(authToken, authToken)
            authenticationManager!!.authenticate(auth).map { authentication: Authentication? -> SecurityContextImpl(authentication) }
        } else {
            Mono.empty()
        }
    }

    companion object {
        private val logger = LoggerFactory.getLogger(SecurityContextRepository::class.java)
        private const val TOKEN_PREFIX = "Bearer "
    }
}
