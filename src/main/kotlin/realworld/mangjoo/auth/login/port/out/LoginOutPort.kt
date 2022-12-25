package realworld.mangjoo.auth.login.port.out

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.adpater.out.LoginOutAdapter
import realworld.mangjoo.auth.login.exception.LoginException
import realworld.mangjoo.user.adapter.out.user.UserEntity
import realworld.mangjoo.user.domain.User


interface LoginOutPort {
    fun findByEmailAndPassword(loginDto: UserLoginDto): User

    @Component
    class LoginOutPortImpl(
        private val outAdapter: LoginOutAdapter
    ) : LoginOutPort {
        val logger = LoggerFactory.getLogger("Login")!!

        override fun findByEmailAndPassword(loginDto: UserLoginDto): User =
            outAdapter.findByEmailAndPassword(loginDto.email, loginDto.password)
                .also { logger.info("login") }
                ?.let { UserEntity.convertEntityToDomain(it) }
                ?: throw LoginException("로그인 할 수 없습니다.")


    }
}