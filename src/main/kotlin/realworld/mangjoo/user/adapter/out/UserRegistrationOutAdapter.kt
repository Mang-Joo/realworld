package realworld.mangjoo.user.adapter.out

import org.springframework.data.jpa.repository.JpaRepository
import realworld.mangjoo.user.adapter.out.user.UserEntity

interface UserRegistrationOutAdapter: JpaRepository<UserEntity, Long> {

    fun findUserEntityByEmail(email: String): UserEntity
}