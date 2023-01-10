package realworld.mangjoo.user.domain

data class User(
    val userAccount: UserAccount,
    val bio: String,
    val image: String?,
    val isAccountNonExpired: Boolean,
    val isAccountNoneLock: Boolean,
    val isCredentialsNonExpired: Boolean,
    val isEnabled: Boolean,
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

}




