package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.UserAccount
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserRegisterRequestDto(
    val email: String,
    val password: String,
    val username: String
) {
    init {
        require(email.isNotBlank()) { throw IllegalArgumentException("email is blank") }
        require(password.isNotBlank()) { throw IllegalArgumentException("password is blank") }
        require(username.isNotBlank()) { throw IllegalArgumentException("username is blank") }
        require(password.matches(Regex("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$"))) { throw IllegalArgumentException("password is not valid") }
    }
    fun encrpytPassword( encryptPassword:String): UserAccount = UserAccount(
        email,
        encryptPassword,
        username
    )
}

