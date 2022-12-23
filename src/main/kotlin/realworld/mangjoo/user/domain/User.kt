package realworld.mangjoo.user.domain

data class User(
    val userAccount: UserAccount,
    val token: String,
    val bio: String,
    val image: String?
)




