package realworld.mangjoo.user.port.out

import org.springframework.stereotype.Component
import realworld.mangjoo.user.adapter.out.UserOutAdapter
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User

fun interface UpdateUserOutPort {
    fun updateUser(user: User): User

    @Component
    class UpdateUserOut(
        private val userOutAdapter: UserOutAdapter
    ) : UpdateUserOutPort {
        override fun updateUser(user: User): User =
            userOutAdapter
                .save(UserEntity.convertDomainToEntity(user))
                .let { UserEntity.convertEntityToDomain(it) }
    }
}