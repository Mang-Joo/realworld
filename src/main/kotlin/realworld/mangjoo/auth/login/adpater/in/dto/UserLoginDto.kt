package realworld.mangjoo.auth.login.adpater.`in`.dto

import org.springframework.security.crypto.password.PasswordEncoder
import realworld.mangjoo.user.domain.User

data class UserLoginDto(
    val email: String,
    val password: String
){
    fun encodePassword(passwordEncoder: PasswordEncoder): UserLoginDto =
        UserLoginDto(
            email,
            passwordEncoder.encode(password)
        )
}

data class UserLoginResponse(
    val email: String,
    val username: String,
    val bio: String,
    val image: String?
) {
    companion object {
        fun convertDomainToDto(user: User): UserLoginResponse {
            return UserLoginResponse(
                user.userAccount.email,
                user.userAccount.userName,
                user.bio,
                user.image
            )
        }
    }
}
