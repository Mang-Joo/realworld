package realworld.mangjoo.auth.login.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User

interface LoginUseCase {
    fun loginUser(userLoginDto: LoginRequest): User

    @Service
    class LoginUseCaseImpl(
        private val loginOutPort: LoginOutPort,
//        private val passwordEncoder: PasswordEncoder,
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase
    ) : LoginUseCase {
        override fun loginUser(userLoginDto: LoginRequest): User {
            //todo: passwordEncode
//            val user: User = loginOutPort.findByEmailAndPassword(userLoginDto.encodePassword(passwordEncoder))
            val user = loginOutPort.findByEmailAndPassword(userLoginDto)
            val createToken = jwtCreateTokenUseCase.createToken(user.userAccount.email)
            return user.createToken(createToken)
        }
    }
}