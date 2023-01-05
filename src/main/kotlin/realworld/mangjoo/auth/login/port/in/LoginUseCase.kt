package realworld.mangjoo.auth.login.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.config.AES256Encoder
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginResponse
import realworld.mangjoo.auth.login.port.out.LoginOutPort

interface LoginUseCase {
    fun loginUser(userLoginDto: LoginRequest): LoginResponse

    @Service
    class LoginUseCaseImpl(
        private val loginOutPort: LoginOutPort,
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase,
        private val aeS256Encoder: AES256Encoder
    ) : LoginUseCase {
        override fun loginUser(userLoginDto: LoginRequest): LoginResponse {
            val user = loginOutPort.findByEmailAndPassword(userLoginDto.encryptPassword(aeS256Encoder.encryptAES256(userLoginDto.password)))
            return LoginResponse(user, jwtCreateTokenUseCase.createToken(user.userAccount.email))
        }
    }
}