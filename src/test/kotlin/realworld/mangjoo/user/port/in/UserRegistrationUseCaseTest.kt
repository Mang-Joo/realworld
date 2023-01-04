package realworld.mangjoo.user.port.`in`

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import realworld.mangjoo.auth.config.AES256EncryptionDecryption
import realworld.mangjoo.fixture.Fixture
import realworld.mangjoo.user.domain.UserAccount

class UserRegistrationUseCaseTest {

    @Test
    @DisplayName("유저 회원가입 테스트")
    fun `user register test`() {
        val userRegistrationUseCase: UserRegistrationUseCase = UserRegistrationUseCase.UserRegistration(
            Fixture().fakeJwtCreateToken,
            Fixture().fakeUserRegistrationOutPort,
            AES256EncryptionDecryption(
                "0123456789abcdef",
                "junnylandauthkey1234567891234512"
            )
        )
        val userAccount = UserAccount("lsun606@naver.com", "Abcdefg123!", "김완주")
        val userRegisterResponse = userRegistrationUseCase.registration(userAccount)

        assertThat(userRegisterResponse.email).isEqualTo(userAccount.email)
        assertThat(userRegisterResponse.username).isEqualTo(userAccount.userName)
        assertThat(userRegisterResponse.token).isNotBlank.isNotNull.isNotEmpty


    }
}