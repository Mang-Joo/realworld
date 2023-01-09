package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.CheckJwtTokenUseCase
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.port.out.FindUserOutPort

fun interface GetUserInfoUseCase {
    fun getUserInfo(token: String): User

    @Service
    class GetUserInfo(
        private val checkJwtTokenUseCase: CheckJwtTokenUseCase,
        private val findUserOutPort: FindUserOutPort
    ) : GetUserInfoUseCase {
        override fun getUserInfo(token: String): User =
            checkJwtTokenUseCase.checkToken(token)
                .let { findUserOutPort.findByEmail(email = it) }


    }
}