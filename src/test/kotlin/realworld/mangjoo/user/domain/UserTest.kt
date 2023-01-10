package realworld.mangjoo.user.domain

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class UserTest {

    private val userAccount = UserAccount(
        "wjwan0915@gmail.com",
        "A1234567#",
        "김완주"
    )

    @Test
    @DisplayName("생성 테스트")
    fun `constructor test`() {
        assertDoesNotThrow { User(userAccount, "자기소개", "") }
    }

    @Test
    @DisplayName("유저 정보 업데이트 테스트")
    fun `user update test`() {
        val beforeUser = User(userAccount, "자기소개", "")
        val updateUserAccount = UserAccount(
            "wjwan0915@naver.com",
            "A1234567#",
            "김완주"
        )
        val updateUser = beforeUser.updateUser(updateUserAccount, "", "")

        assertThat(updateUser.email()).isNotEqualTo(beforeUser.email())
    }

}