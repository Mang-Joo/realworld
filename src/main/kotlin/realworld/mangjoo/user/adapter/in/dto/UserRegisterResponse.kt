package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.User

data class UserRegisterResponse(
    val email: String, val token: String, val username: String, val bio: String, val image: String?
) {
    constructor(user: User, token: String) : this(
        user.userAccount.email,
        token,
        user.userAccount.userName,
        user.bio,
        user.image
    )
}
