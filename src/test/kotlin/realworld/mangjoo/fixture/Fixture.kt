package realworld.mangjoo.fixture

import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.UserRegistrationOutPort

class Fixture {
    val fakeUser = LoginOutPort {
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
    val fakeJwtCreateToken = JwtCreateTokenUseCase { "token" }

    val fakeUserRegistrationOutPort: UserRegistrationOutPort = UserRegistrationOutPort { user: User -> user }

}