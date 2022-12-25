package realworld.mangjoo.auth.login.port.`in`

import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User

interface LoginUseCase {
    fun loginUser(userLoginDto: UserLoginDto): User

    @Service
    class LoginUseCaseImpl(
        private val loginOutPort: LoginOutPort,
        private val passwordEncoder: PasswordEncoder,
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase
    ) : LoginUseCase {
        override fun loginUser(userLoginDto: UserLoginDto): User {
            val user: User = loginOutPort.findByEmailAndPassword(userLoginDto.encodePassword(passwordEncoder))
            val createToken = jwtCreateTokenUseCase.createToken(user.userAccount.email)
            return user.createToken(createToken)
        }
    }
}