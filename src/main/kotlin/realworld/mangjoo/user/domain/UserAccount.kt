package realworld.mangjoo.user.domain

import realworld.mangjoo.auth.login.adpater.`in`.EMAIL_ADDRESS

data class UserAccount(
    val email: String,
    val passWord: String,
    val userName: String
) {
    init {
        require(email.isNotBlank()) { throw IllegalArgumentException("email is blank") }
        require(passWord.isNotBlank()) { throw IllegalArgumentException("password is blank") }
        require(email.matches(Regex(EMAIL_ADDRESS))) { throw IllegalArgumentException("email is not valid") }
    }
    fun passwordEncoding(encodingPassWord: String): UserAccount =
        UserAccount(email, encodingPassWord, userName)


}
