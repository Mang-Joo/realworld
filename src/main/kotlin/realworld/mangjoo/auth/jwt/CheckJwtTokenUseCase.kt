package realworld.mangjoo.auth.jwt

import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

fun interface CheckJwtTokenUseCase {
    fun checkToken(token: String): String


    @Component
    class CheckJwtToken(
        @Value("\${jwt.token.secretKey}") val key: String
    ) : CheckJwtTokenUseCase {
        override fun checkToken(token: String): String =
            Jwts.parser()
                .setSigningKey(key.encodeToByteArray())
                .parseClaimsJws(token)
                .body.subject
    }
}