package realworld.mangjoo.user.port.out

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import realworld.mangjoo.user.adapter.out.UserOutAdapter
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User

interface FindUserOutPort {
    fun findByEmail(email: String): User

    @Component
    class FindUser(
        private val userOutAdapter: UserOutAdapter
    ):FindUserOutPort {
        private val logger = LoggerFactory.getLogger("User")!!
        override fun findByEmail(email: String): User {
            return userOutAdapter.findUserEntityByEmail(email)
                .also { logger.info("Get user by {}", email) }
                ?.let { UserEntity.convertEntityToDomain(it) }
                ?: throw IllegalArgumentException("해당 계정이 존재하지 않습니다.")
        }
    }
}