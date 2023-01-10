package realworld.mangjoo.user.domain

data class User(
    val userAccount: UserAccount,
    val bio: String,
    val image: String?,
    val isAccountNonExpired: Boolean = true,
    val isAccountNoneLock: Boolean = true,
    val isCredentialsNonExpired: Boolean = true,
    val isEnabled: Boolean = true,
) {
    constructor(userAccount: UserAccount, bio: String, image: String?) : this(
        userAccount = userAccount,
        bio = bio,
        image = image,
        isAccountNonExpired = true,
        isAccountNoneLock = true,
        isCredentialsNonExpired = true,
        isEnabled = true
    )

    fun updateUser(
        userAccount: UserAccount,
        bio: String,
        image: String
    ) = User(
        userAccount,
        bio,
        image,
        isAccountNonExpired,
        isAccountNoneLock,
        isCredentialsNonExpired,
        isEnabled
    )

    fun email() = userAccount.email

    companion object{
        fun of(userAccount: UserAccount, bio: String): User {
            return User(userAccount,bio, null)
        }

    }
}




