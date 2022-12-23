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

}




