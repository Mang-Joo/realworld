package realworld.mangjoo.auth.jwt

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test


class CheckJwtTokenUseCaseTest {

    @Test
    @DisplayName("토큰 검증 성공 테스트")
    fun `token verification test`() {
        val loginEmail = "wjwan0915@gmail.com"

        val createToken = JwtCreateTokenUseCase
            .JwtCreateTokenProvider("key", 3000L)
            .createToken(loginEmail)


        val email = CheckJwtTokenUseCase.CheckJwtToken("key")
            .checkToken(createToken)

        assertThat(loginEmail).isEqualTo(email)

    }

    @Test
    @DisplayName("토큰 검증 실패 테스트")
    fun `token vierfication fail test`() {
        val loginEmail = "wjwan0915@gmail.com"

        val createToken = JwtCreateTokenUseCase
            .JwtCreateTokenProvider("key", 3000L)
            .createToken(loginEmail)


        assertThatThrownBy { CheckJwtTokenUseCase.CheckJwtToken("key")
            .checkToken(createToken.replace("a", "b")) }
            .isInstanceOf(io.jsonwebtoken.SignatureException::class.java)


    }
}