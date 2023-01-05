package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.config.AES256EncryptionDecryption
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
        private val userRegistrationOutPort: UserRegistrationOutPort,
        private val aeS256EncryptionDecryption: AES256EncryptionDecryption
    ) : UserRegistrationUseCase {
        override fun registration(userAccount: UserAccount): UserRegisterResponse {
            val saveUser = User(
                userAccount = userAccount,
                null,
                bio = "I work at state farm",
                image = null,
                isAccountNonExpired = true,
                isAccountNoneLock = true,
                isCredentialsNonExpired = true,
                isEnabled = true
            ).let { userRegistrationOutPort.save(it) }

            return UserRegisterResponse(saveUser, jwtCreateTokenUseCase.createToken(saveUser.userAccount.email))
        }
    }
}