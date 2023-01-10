package realworld.mangjoo.auth.login.port.`in`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase.JwtCreateTokenProvider
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.UserResponse
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase.LoginUseCaseImpl
import realworld.mangjoo.fixture.Fixture

class LoginUseCaseTest {
    private val loginUseCase: LoginUseCase = LoginUseCaseImpl(
        loginOutPort = Fixture().fakeUser,
        jwtCreateTokenUseCase = JwtCreateTokenProvider("mangjoo", 1000),
        passWordEncoder = Fixture().aeS256Encoder
    )

    @Test
    fun loginTest() {
        val userLoginDto = LoginRequest("mangjoo@naver.com", "A1234567#")
        val loginUser: UserResponse = loginUseCase.loginUser(userLoginDto)
        assertThat(userLoginDto.email).isEqualTo(loginUser.email)
    }
}

