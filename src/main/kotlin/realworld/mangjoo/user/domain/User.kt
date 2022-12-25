package realworld.mangjoo.user.domain

data class User(
    val userAccount: UserAccount,
    val token: String?,
    val bio: String,
    val image: String?,
    val isAccountNonExpired: Boolean,
    val isAccountNoneLock: Boolean,
    val isCredentialsNonExpired: Boolean,
    val isEnabled: Boolean,
) {
    fun createToken(token: String): User {
        return User(
            this.userAccount,
            token,
            this.bio,
            this.image,
            this.isAccountNonExpired,
            this.isAccountNoneLock,
            this.isCredentialsNonExpired,
            this.isEnabled
        )
    }
}




