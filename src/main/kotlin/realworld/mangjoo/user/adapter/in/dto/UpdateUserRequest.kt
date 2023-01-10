package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.UserAccount

data class UpdateUserRequest(
    val email: String,
    val userName: String,
    val passWord: String,
    val bio: String,
    val image: String
) {
    fun toDomainUserAccount() =
        UserAccount(
            email = email,
            passWord = passWord,
            userName = userName
        )

}