package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.UserRegistrationOutPort

interface UserRegistrationUseCase {
    fun registration(userAccount: UserAccount): UserRegisterResponse


    @Service
    class UserRegistration(
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase,
        private val userRegistrationOutPort: UserRegistrationOutPort
    ) : UserRegistrationUseCase {
        override fun registration(userAccount: UserAccount): UserRegisterResponse =
            User(
                userAccount = userAccount,
                bio = "I work at state farm",
                image = null,
                isAccountNonExpired = true,
                isAccountNoneLock = true,
                isCredentialsNonExpired = true,
                isEnabled = true
            )
                .let { userRegistrationOutPort.save(it) }
                .let { UserRegisterResponse(it, jwtCreateTokenUseCase.createToken(it.userAccount.email)) }
    }
}
