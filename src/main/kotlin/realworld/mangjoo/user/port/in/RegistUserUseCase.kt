package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.RegistUserOutPort

interface RegistUserUseCase {
    fun registration(userAccount: UserAccount): UserRegisterResponse


    @Service
    class RegistUser(
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase,
        private val registUserOutPort: RegistUserOutPort
    ) : RegistUserUseCase {
        override fun registration(userAccount: UserAccount): UserRegisterResponse = User.of(userAccount, "I work at state farm")
                .let { registUserOutPort.save(it) }
                .let { UserRegisterResponse(it, jwtCreateTokenUseCase.createToken(it.userAccount.email)) }
    }
}
