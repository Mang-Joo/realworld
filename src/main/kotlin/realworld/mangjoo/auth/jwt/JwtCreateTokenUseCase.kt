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
        private val now = Date()
        override fun createToken(email: String): String =
            Jwts.builder()
                .setHeader(header(email))
                .setClaims(body(email))
                .setIssuedAt(now)
                .setExpiration(Date(now.time + time))
                .signWith(SignatureAlgorithm.HS256, key.encodeToByteArray())
                .compact()

        private fun header(email: String) = Jwts.header().also { it["key"] = email }
        private fun body(email: String) = Jwts.claims()
            .also { it.subject = email }
            .also { it["password"] = email }
    }
}