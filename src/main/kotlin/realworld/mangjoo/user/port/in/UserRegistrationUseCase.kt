package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtTokenUseCase
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.UserRegistrationOutPort

interface UserRegistrationUseCase {
    fun registration(userAccount: UserAccount): UserRegisterResponse


    @Service
    class UserRegistration(
        private val jwtTokenUseCase: JwtTokenUseCase,
        private val userRegistrationOutPort: UserRegistrationOutPort
    ) : UserRegistrationUseCase {
        override fun registration(userAccount: UserAccount): UserRegisterResponse {
            val applicationUser = User(
                userAccount = userAccount,
                bio = "I work at statefarm",
                image = null,
                isAccountNonExpired = true,
                isAccountNoneLock = true,
                isCredentialsNonExpired = true,
                isEnabled = true
            )
            val saveUser = userRegistrationOutPort.save(applicationUser)


            val token = jwtTokenUseCase.createToken(saveUser.userAccount.email)


            return UserRegisterResponse.convertDomainToDto(saveUser, token)
        }
    }
}