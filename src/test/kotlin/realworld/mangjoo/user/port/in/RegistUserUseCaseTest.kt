package realworld.mangjoo.user.port.`in`

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import realworld.mangjoo.fixture.Fixture
import realworld.mangjoo.user.domain.UserAccount

class RegistUserUseCaseTest {

    @Test
    @DisplayName("유저 회원가입 테스트")
    fun `user register test`() {
        val registUserUseCase: RegistUserUseCase = RegistUserUseCase.RegistUser(
            Fixture().fakeJwtCreateToken,
            Fixture().fakeRegistUserOutPort
        )
        val userAccount = UserAccount("lsun606@naver.com", "Abcdefg123!", "김완주")
        val userRegisterResponse = registUserUseCase.registration(userAccount)

        assertThat(userRegisterResponse.email).isEqualTo(userAccount.email)
        assertThat(userRegisterResponse.username).isEqualTo(userAccount.userName)
        assertThat(userRegisterResponse.token).isNotBlank.isNotNull.isNotEmpty


    }
}