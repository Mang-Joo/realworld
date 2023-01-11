package realworld.mangjoo.user.domain

import java.util.*

data class User(
    val userID: UUID,
    val userAccount: UserAccount,
    val bio: String,
    val image: String?,
    val isAccountNonExpired: Boolean = true,
    val isAccountNoneLock: Boolean = true,
    val isCredentialsNonExpired: Boolean = true,
    val isEnabled: Boolean = true,
) {
    constructor(userAccount: UserAccount, bio: String, image: String?) : this(
        userID = UUID.randomUUID(),
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
        userID,
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




