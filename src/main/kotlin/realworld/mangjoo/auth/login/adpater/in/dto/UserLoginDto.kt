package realworld.mangjoo.auth.login.adpater.`in`.dto

import realworld.mangjoo.user.domain.User
import javax.validation.constraints.Email
import javax.validation.constraints.NotNull

data class UserLoginDto(
    @field:Email(message = "Email format is incorrect.")
    @field:NotNull(message = "Email is required.")
    val email: String,
    @field:NotNull(message = "The password must be entered unconditionally.")
    val password: String
) {
//    fun encodePassword(passwordEncoder: PasswordEncoder): UserLoginDto =
//        UserLoginDto(
//            email,
//            passwordEncoder.encode(password)
//        )
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
