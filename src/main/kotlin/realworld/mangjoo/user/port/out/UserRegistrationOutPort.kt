package realworld.mangjoo.user.port.out

import org.springframework.stereotype.Component
import realworld.mangjoo.user.adapter.out.UserRegistrationOutAdapter
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User

fun interface UserRegistrationOutPort {
    fun save(user: User): User

    @Component
    class UserRegistrationOut(private val userRegistrationOutAdapter: UserRegistrationOutAdapter) :
        UserRegistrationOutPort {
        override fun save(user: User): User {
            val userEntity = userRegistrationOutAdapter.save(UserEntity.convertDomainToEntity(user))
            return UserEntity.convertEntityToDomain(userEntity)
        }

    }

}