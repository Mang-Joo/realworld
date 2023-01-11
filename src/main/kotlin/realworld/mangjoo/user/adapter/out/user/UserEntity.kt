package realworld.mangjoo.user.adapter.out.user

import org.hibernate.annotations.DynamicInsert
import org.hibernate.annotations.DynamicUpdate
import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import realworld.mangjoo.user.adapter.out.BaseTimeEntity
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "userTable")
@SQLDelete(sql = "UPDATE userTable SET is_enabled = false WHERE id = ?")
@Where(clause = "is_enabled=true")
@DynamicInsert
@DynamicUpdate
class UserEntity(
    @Id
    var id: UUID,
    @Column(unique = true)
    var email: String,
    var password: String,
    var username: String,
    var bio: String,
    var image: String? = null,
    var isAccountNonExpired: Boolean,
    var isAccountNoneLock: Boolean,
    var isCredentialsNonExpired: Boolean,
    var isEnabled: Boolean,
): BaseTimeEntity() {
    companion object {
        fun convertEntityToDomain(userEntity: UserEntity): User {
            return User(
                userEntity.id,
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
                user.userID,
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