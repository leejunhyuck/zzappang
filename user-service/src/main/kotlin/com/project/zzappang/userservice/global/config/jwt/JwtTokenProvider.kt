package com.project.zzappang.userservice.global.config.jwt


import com.project.zzappang.userservice.domain.user.model.User
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import java.util.function.Function



@Component
class JwtTokenProvider(
        private val ACCESS_TOKEN_VALIDITY_SECONDS : Long = 5*60*60,
        private val SIGNING_KEY : String = "hello",
        private val AUTHORITIES_KEY : String = "world"
) : Serializable {

    fun getUsernameFromToken(token: String?): String {
        return getClaimFromToken(token, Function { obj: Claims -> obj.subject })
    }

    fun getExpirationDateFromToken(token: String?): Date {
        return getClaimFromToken(token, Function { obj: Claims -> obj.expiration })
    }

    fun <T> getClaimFromToken(token: String?, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    fun getAllClaimsFromToken(token: String?): Claims {
        return Jwts.parser()
                .setSigningKey(SIGNING_KEY)
                .parseClaimsJws(token)
                .getBody()
    }

    fun isTokenExpired(token: String?): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: User?): String {

        val authorities: List<String>? = user?.roles?.map { it.name!! }
        val claims: Claims = Jwts.claims().setSubject(user?.name)
        claims["roles"] = authorities


        return Jwts.builder()
                .setSubject(user?.name)
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS*1000))
                .compact();

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
                .setIssuedAt(Date())
                .setExpiration(Date(ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
                .compact()


    }
}