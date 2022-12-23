package realworld.mangjoo.user.port.out

import org.springframework.stereotype.Component
import realworld.mangjoo.user.adapter.out.UserRegistrationOutAdapter
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User

interface UserRegistrationOutPort {
    fun save(user: User): User
    fun findByEmail(email: String): User

    @Component
    class UserRegistrationOut(private val userRegistrationOutAdapter: UserRegistrationOutAdapter) :
        UserRegistrationOutPort {
        override fun save(user: User): User {
            val userEntity = userRegistrationOutAdapter.save(UserEntity.convertDomainToEntity(user))
            return UserEntity.convertEntityToDomain(userEntity)
        }

        override fun findByEmail(email: String): User {

            val userEntity = userRegistrationOutAdapter.findUserEntityByEmail(email)
            return UserEntity.convertEntityToDomain(userEntity)
        }
    }

}