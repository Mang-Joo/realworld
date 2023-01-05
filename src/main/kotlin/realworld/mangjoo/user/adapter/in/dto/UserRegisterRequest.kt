package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.UserAccount

const val EMAIL_ADDRESS: String = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
const val PASSWORD: String = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,}\$"

data class UserRegisterRequest(
    val email: String,
    val password: String,
    val username: String
) {
    init {
        require(email.isNotBlank()) { throw IllegalArgumentException("email is blank") }
        require(password.isNotBlank()) { throw IllegalArgumentException("password is blank") }
        require(username.isNotBlank()) { throw IllegalArgumentException("username is blank") }
        require(email.matches(Regex(EMAIL_ADDRESS))) {throw IllegalArgumentException("email is not valid")}
        require(password.matches(Regex(PASSWORD))) { throw IllegalArgumentException("password is not valid") }
    }
    fun encryptPasswordToUserAccount(encryptPassword:String): UserAccount = UserAccount(
        email,
        encryptPassword,
        username
    )
}

