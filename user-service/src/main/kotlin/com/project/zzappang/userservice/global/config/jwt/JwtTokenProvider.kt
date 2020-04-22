package com.project.zzappang.userservice.global.config.jwt

import com.project.zzappang.userservice.global.config.security.CustomUserService
import org.springframework.beans.factory.annotation.Autowired
import io.jsonwebtoken.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
        @Qualifier("customUserDetailsService")
        @Autowired
        private val userDetailsService: CustomUserService
) {
    private val secretKey: String = "jwt_secret_geunyoung"
    private val validityInMilliseconds: Long = 36000000

    fun createToken(username: String, roles: List<String>): String {
        val claims: Claims = Jwts.claims().setSubject(username)
        claims["roles"] = roles

        val validity = Date(Date().time + validityInMilliseconds)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(Date())
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS512, secretKey)
                .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val userDetails : UserDetails = userDetailsService.loadUserByUsername(getUsername(token))
        return UsernamePasswordAuthenticationToken(userDetails, "", userDetails.authorities)
    }

    fun getUsername(token: String): String {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .body.subject
    }

    fun resolveToken(req : HttpServletRequest): String? {
        val bearerToken: String? = req.getHeader("Authorization")
        bearerToken?: return null

        if(!bearerToken.startsWith("Bearer ")) return null

        return bearerToken.substring(7, bearerToken.length)
    }

    fun validateToken(token: String): Boolean {
        try{
            val claims: Jws<Claims> = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            if(claims.body.expiration.before(Date())) return false
            return true
        } catch (e: Exception) {
            throw JwtValidationException(e.message)
        }
    }
}