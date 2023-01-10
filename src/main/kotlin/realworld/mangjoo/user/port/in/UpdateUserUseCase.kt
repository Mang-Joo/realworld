package realworld.mangjoo.user.port.`in`

import org.springframework.stereotype.Service
import realworld.mangjoo.auth.config.PassWordEncoder
import realworld.mangjoo.auth.jwt.CheckJwtTokenUseCase
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.FindUserOutPort
import realworld.mangjoo.user.port.out.UpdateUserOutPort

fun interface UpdateUserUseCase {
    fun updateUser(
        token: String,
        userAccount: UserAccount,
        updateBio: String,
        updateImage: String
    ): User

    @Service
    class UpdateUser(
        private val findUserOutPort: FindUserOutPort,
        private val updateUserOutPort: UpdateUserOutPort,
        private val checkJwtTokenUseCase: CheckJwtTokenUseCase,
        private val passWordEncoder: PassWordEncoder
    ) : UpdateUserUseCase {
        override fun updateUser(
            token: String,
            userAccount: UserAccount,
            updateBio: String,
            updateImage: String
        ): User = checkJwtTokenUseCase.checkToken(token)
            .let { findUserOutPort.findByEmail(it) }
            .also {
                val encodingUserAccount = userAccount.passwordEncoding(passWordEncoder.encryptAES256(userAccount.passWord))
                it.updateUser(encodingUserAccount, bio = updateBio, image = updateImage)
            }
            .also { updateUserOutPort.updateUser(it) }
    }
}