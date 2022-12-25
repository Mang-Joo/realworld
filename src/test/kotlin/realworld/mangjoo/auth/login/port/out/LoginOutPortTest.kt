package realworld.mangjoo.auth.login.port.out

import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.exception.LoginException
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.UserRegistrationOutPort

@SpringBootTest
class LoginOutPortTest(
    @Autowired
    val loginOutPort: LoginOutPort,

    @Autowired
    val userRegistrationOutPort: UserRegistrationOutPort
) {

    @Test
    @DisplayName("로그인 실패 테스트")
    fun test() {
        assertThatThrownBy { loginOutPort.findByEmailAndPassword(UserLoginDto("mangjoo", "1234")) }
            .isInstanceOf(LoginException::class.java)
            .hasMessage("로그인 할 수 없습니다.")
    }

    @Test
    @DisplayName("로그인 성공 테스트")
    fun loginSuccess() {
        val createUser = User(
            UserAccount("mangjoo", "1234", "망주"),
            null,
            bio = "",
            image = null,
            isAccountNonExpired = true,
            isAccountNoneLock = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        )
        userRegistrationOutPort.save(createUser)

        val findByEmailAndPassword = loginOutPort.findByEmailAndPassword(UserLoginDto("mangjoo", "1234"))
        assertThat(createUser.userAccount.email).isEqualTo(findByEmailAndPassword.userAccount.email)
    }
}