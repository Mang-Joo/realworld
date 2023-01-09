package realworld.mangjoo.user.port.`in`

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import realworld.mangjoo.auth.jwt.CheckJwtTokenUseCase
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.fixture.Fixture

class GetUserInfoUseCaseTest {

    @Test
    @DisplayName("토큰 전달 후 이메일 찾기 테스트")
    fun `send token then get email`() {
        val getUserInfoUseCase:GetUserInfoUseCase = GetUserInfoUseCase.GetUserInfo(
            CheckJwtTokenUseCase.CheckJwtToken("key"),
            Fixture().fakeFindUserOutPort
        )

        val createToken = JwtCreateTokenUseCase
            .JwtCreateTokenProvider("key", 3000L)
            .createToken(Fixture().loginEmail)


        val userInfo = getUserInfoUseCase.getUserInfo(createToken)

        assertThat(userInfo.userAccount.email).isEqualTo(Fixture().loginEmail)

    }

}