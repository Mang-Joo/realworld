package realworld.mangjoo.user.adapter.out.user

import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var email: String,
    var password: String,
    var username: String,
    var bio: String,
    var image: String? = null,
    var isAccountNonExpired: Boolean,
    var isAccountNoneLock: Boolean,
    var isCredentialsNonExpired: Boolean,
    var isEnabled: Boolean,
) {
    companion object {
        fun convertEntityToDomain(userEntity: UserEntity): User {
            return User(
                UserAccount(
                    userEntity.email,
                    userEntity.password,
                    userEntity.username
                ),
                userEntity.bio,
                userEntity.image,
                userEntity.isAccountNonExpired,
                userEntity.isAccountNoneLock,
                userEntity.isCredentialsNonExpired,
                userEntity.isEnabled,
            )
        }

        fun convertDomainToEntity(user: User): UserEntity {
            return UserEntity(
                null,
                user.userAccount.email,
                user.userAccount.passWord,
                user.userAccount.userName,
                user.bio,
                user.image,
                user.isAccountNonExpired,
                user.isAccountNoneLock,
                user.isCredentialsNonExpired,
                user.isEnabled
            )
        }
    }
}