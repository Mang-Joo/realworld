package realworld.mangjoo.fixture

import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount

class FakeLoginOutPort : LoginOutPort {
    override fun findByEmailAndPassword(loginDto: UserLoginDto): User =
        User(
            UserAccount("mangjoo", "1234", "망주"),
            null,
            bio = "",
            image = null,
            isAccountNonExpired = true,
            isAccountNoneLock = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        )
}
