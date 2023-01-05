package realworld.mangjoo.auth.login.port.out

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.LoginRequest
import realworld.mangjoo.auth.login.adpater.out.LoginOutAdapter
import realworld.mangjoo.auth.login.exception.login.LoginException
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User
import javax.transaction.Transactional


fun interface LoginOutPort {
    fun findByEmailAndPassword(loginDto: LoginRequest): User

    @Component
    @Transactional
    class LoginOutPortImpl(
        private val outAdapter: LoginOutAdapter
    ) : LoginOutPort {
        val logger = LoggerFactory.getLogger("Login")!!

        override fun findByEmailAndPassword(loginDto: LoginRequest): User =
            outAdapter.findByEmailAndPassword(loginDto.email, loginDto.password)
                .also { logger.info("login") }
                ?.let { UserEntity.convertEntityToDomain(it) }
                ?: throw LoginException("CanNotLogin", "게정 혹은 비밀번호가 틀립니다.")
    }
}