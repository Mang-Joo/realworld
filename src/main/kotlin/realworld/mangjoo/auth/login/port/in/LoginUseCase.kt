package realworld.mangjoo.auth.login.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.config.AES256EncryptionDecryption
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User

interface LoginUseCase {
    fun loginUser(userLoginDto: LoginRequest): User

    @Service
    class LoginUseCaseImpl(
        private val loginOutPort: LoginOutPort,
        private val jwtCreateTokenUseCase: JwtCreateTokenUseCase,
        private val aeS256EncryptionDecryption: AES256EncryptionDecryption
    ) : LoginUseCase {
        override fun loginUser(userLoginDto: LoginRequest): User {
            val user = loginOutPort.findByEmailAndPassword(userLoginDto.encryptPassword(aeS256EncryptionDecryption.encryptAES256(userLoginDto.password)))
            val createToken = jwtCreateTokenUseCase.createToken(user.userAccount.email)
            return user.createToken(createToken)
        }
    }
}