package realworld.mangjoo.auth.login.port.`in`

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase.JwtCreateTokenProvider
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase.LoginUseCaseImpl
import realworld.mangjoo.fixture.Fixture

const val EMAIL_ADDRESS: String = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
const val PASSWORD: String = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,}\$"
class LoginUseCaseTest {
    private val loginUseCase: LoginUseCase = LoginUseCaseImpl(
        loginOutPort = Fixture().fakeUser,
        jwtCreateTokenUseCase = JwtCreateTokenProvider("mangjoo", 1000),
        aeS256EncryptionDecryption = Fixture().aeS256EncryptionDecryption
    )

    @Test
    fun loginTest() {
        val userLoginDto = LoginRequest("mangjoo@naver.com", "A1234567#")

        val loginUser = loginUseCase.loginUser(userLoginDto)
        assertThat(userLoginDto.email).isEqualTo(loginUser.userAccount.email)
        assertThat(userLoginDto.password).isEqualTo(loginUser.userAccount.passWord)
    }
}

