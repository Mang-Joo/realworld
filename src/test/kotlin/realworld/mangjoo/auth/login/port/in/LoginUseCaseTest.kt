package realworld.mangjoo.auth.login.port.`in`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.security.crypto.factory.PasswordEncoderFactories.createDelegatingPasswordEncoder
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase.JwtCreateTokenProvider
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase.LoginUseCaseImpl
import realworld.mangjoo.fixture.FakeLoginOutPort

class LoginUseCaseTest {
    val loginUseCase: LoginUseCase = LoginUseCaseImpl(
        loginOutPort = FakeLoginOutPort(),
        passwordEncoder = createDelegatingPasswordEncoder(),
        jwtCreateTokenUseCase = JwtCreateTokenProvider("mangjoo", 1000)
    )
    @Test
    fun loginTest() {
        val userLoginDto: UserLoginDto = UserLoginDto("mangjoo", "abc")

        val loginUser = loginUseCase.loginUser(userLoginDto)

        assertThat(userLoginDto.email).isEqualTo(loginUser.userAccount.email)

    }
}