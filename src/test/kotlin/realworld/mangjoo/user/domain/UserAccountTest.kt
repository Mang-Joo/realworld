package realworld.mangjoo.user.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import realworld.mangjoo.auth.config.PassWordEncoder

class UserAccountTest {

    @Test
    @DisplayName("생성 테스트")
    fun `constructor test`() {
        assertDoesNotThrow {
            UserAccount(
                "wjwan0915@gmail.com",
                "A1234567#",
                "김완주"
            )
        }
    }

    @Test
    @DisplayName("생성 예외 테스트")
    fun `constructor exception test`() {
        assertThatThrownBy {
            UserAccount(
                "wjwan0915",
                "A1234567#",
                "김완주"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("email is not valid")

        assertThatThrownBy {
            UserAccount(
                "",
                "A1234567#",
                "김완주"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("email is blank")

        assertThatThrownBy {
            UserAccount(
                "wjwan0915@gmail.com",
                "",
                "김완주"
            )
        }.isInstanceOf(IllegalArgumentException::class.java)
            .hasMessage("password is blank")
    }

    @Test
    @DisplayName("유저 비밀번호 인코딩 테스트")
    fun `user update test`() {
        val encodePassWord = "rprtNdQ6hLGh2p4Noy/Mqw=="
        val passWordEncoder = PassWordEncoder("0123456789abcdef", "junnylandauthkey1234567891234512")
        val beforeUserAccount = UserAccount(
            "wjwan0915@gmail.com",
            "A1234567#",
            "김완주"
        )
        val afterUserAccount = beforeUserAccount
            .passwordEncoding(passWordEncoder.encryptAES256(beforeUserAccount.passWord))

        assertThat(afterUserAccount.passWord).isEqualTo(encodePassWord)

    }
}