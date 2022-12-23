package realworld.mangjoo.user.port.`in`

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtTokenUseCase
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount

interface UserRegistrationUseCase {
    fun registration(userAccount: UserAccount): User


    @Service
    class UserRegistration(
        @Autowired val jwtTokenUseCase: JwtTokenUseCase
    ) : UserRegistrationUseCase {
        override fun registration(userAccount: UserAccount): User {

            val token = jwtTokenUseCase.createToken(userAccount.email)

            return User(userAccount, token, "I work at statefarm", null)
        }
    }
}