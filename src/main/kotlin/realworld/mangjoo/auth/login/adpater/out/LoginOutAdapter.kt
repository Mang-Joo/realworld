package realworld.mangjoo.auth.login.adpater.out

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import realworld.mangjoo.user.adapter.out.user.UserEntity

@Repository
interface LoginOutAdapter : JpaRepository<UserEntity, Long> {
    fun findByEmailAndPassword(email: String, passWord: String): UserEntity?
}