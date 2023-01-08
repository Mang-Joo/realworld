package realworld.mangjoo.auth.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

fun interface JwtCreateTokenUseCase {
    fun createToken(email: String): String


    @Component
    class JwtCreateTokenProvider(
        @Value("\${jwt.token.secretKey}") val key: String,
        @Value("\${jwt.token.time}") val time: Long
    ) : JwtCreateTokenUseCase {
        override fun createToken(email: String): String {
            val claims = Jwts.claims()
                .also { it.subject = email }
                .also { it["password"] = email }

            val header = Jwts.header()
                .also { it["key"] = email }

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