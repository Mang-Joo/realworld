package realworld.mangjoo.auth.jwt

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

interface JwtTokenUseCase {
    fun createToken(email: String): String


    @Component
    class JwtTokenProvider(
        @Value("\${jwt.token.secretKey}") val key: String,
        @Value("\${jwt.token.time}") val time: Long
    ) : JwtTokenUseCase {
        override fun createToken(email: String): String {
            val claims: Claims = Jwts.claims().setSubject(email)
            claims["password"] = email
            val header = Jwts.header()
            header["key"] = email
            val now = Date()
            return Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(Date(now.time + time))
                .signWith(SignatureAlgorithm.HS256, key.encodeToByteArray())
                .compact()
        }
    }
}