package realworld.mangjoo.user.port.out

import org.springframework.stereotype.Component
import realworld.mangjoo.user.adapter.out.UserOutAdapter
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User

fun interface RegistUserOutPort {
    fun save(user: User): User

    @Component
    class RegistUserOut(
        private val userOutAdapter: UserOutAdapter,
    ) :
        RegistUserOutPort {
        override fun save(user: User): User =
            userOutAdapter
                .save(UserEntity.convertDomainToEntity(user))
                .let { UserEntity.convertEntityToDomain(it) }
    }

}