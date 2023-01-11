package realworld.mangjoo.auth.login.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.config.PassWordEncoder
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.UserResponse
import realworld.mangjoo.auth.login.port.out.LoginOutPort

interface LoginUseCase {
    fun loginUser(userLoginDto: LoginRequest): UserResponse

    @Service
    class LoginUseCaseImpl(
        private val loginOutPort: LoginOutPort,
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase,
        private val passWordEncoder: PassWordEncoder
    ) : LoginUseCase {
        override fun loginUser(userLoginDto: LoginRequest): UserResponse = loginOutPort
                .findByEmailAndPassword(userLoginDto.encryptPassword(passWordEncoder.encryptAES256(userLoginDto.password)))
                .let { UserResponse(it, jwtCreateTokenUseCase.createToken(it.userAccount.email)) }
    }

    data class LoginUseCaseRequest(
        val email: String,
        val password: String,
    )
}