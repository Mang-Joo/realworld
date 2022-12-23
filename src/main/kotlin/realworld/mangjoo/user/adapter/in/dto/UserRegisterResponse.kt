package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.User

data class UserRegisterResponse(
    val email: String, val token: String, val username: String, val bio: String, val image: String?
) {
    companion object {
        fun convertDomainToDto(user: User): UserRegisterResponse {
            return UserRegisterResponse(
                user.userAccount.email, user.token, user.userAccount.userName, user.bio, user.image
            )
        }

    }
}
