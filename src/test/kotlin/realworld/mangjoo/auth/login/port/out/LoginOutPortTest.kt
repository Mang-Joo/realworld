package realworld.mangjoo.auth.login.port.out

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import realworld.mangjoo.auth.config.AES256Encoder
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.*
import realworld.mangjoo.auth.login.exception.login.LoginException
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.UserRegistrationOutPort

@SpringBootTest
class LoginOutPortTest(
    @Autowired
    val loginOutPort: LoginOutPort,

    @Autowired
    val userRegistrationOutPort: UserRegistrationOutPort,
    @Autowired
    val aeS256Encoder: AES256Encoder

) {

    @Test
    @DisplayName("로그인 실패 테스트")
    fun test() {
        assertThatThrownBy { loginOutPort.findByEmailAndPassword(LoginRequest("mangjoo@naver.com", "A1234567#")) }
            .isInstanceOf(LoginException::class.java)
            .hasMessage("게정 혹은 비밀번호가 틀립니다.")
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    fun loginSuccess() {
        val createUser = User(
            UserAccount("mangjoo@naver.com", aeS256Encoder.encryptAES256("A1234567#"), "망주"),
            bio = "",
            image = null,
            isAccountNonExpired = true,
            isAccountNoneLock = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        )
        userRegistrationOutPort.save(createUser)

        val findByEmailAndPassword = loginOutPort.findByEmailAndPassword(LoginRequest("mangjoo@naver.com", aeS256Encoder.encryptAES256("A1234567#")))
        assertThat(createUser.userAccount.email).isEqualTo(findByEmailAndPassword.userAccount.email)
    }
}