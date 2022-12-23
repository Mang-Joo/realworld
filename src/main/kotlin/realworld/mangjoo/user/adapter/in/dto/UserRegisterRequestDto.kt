package realworld.mangjoo.user.adapter.`in`.dto

import org.mindrot.jbcrypt.BCrypt
import realworld.mangjoo.user.domain.UserAccount

data class UserRegisterRequestDto(
    private val email: String,
    private val password: String,
    private val username: String
){
    fun convertDtoToDomain(): UserAccount {
        val passwordHashed = BCrypt.hashpw(password, BCrypt.gensalt(10))

        return UserAccount(email, passwordHashed, username)
    }
}

