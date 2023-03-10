package realworld.mangjoo.fixture

import realworld.mangjoo.auth.config.PassWordEncoder
import realworld.mangjoo.auth.jwt.JwtCreateTokenUseCase
import realworld.mangjoo.auth.login.port.out.LoginOutPort
import realworld.mangjoo.user.domain.User
import realworld.mangjoo.user.domain.UserAccount
import realworld.mangjoo.user.port.out.FindUserOutPort
import realworld.mangjoo.user.port.out.RegistUserOutPort
import java.util.*

class Fixture {
    val fakeUser = LoginOutPort {
        User(
            UUID.randomUUID(),
            UserAccount("mangjoo@naver.com", "A1234567#", "망주"),
            bio = "",
            image = null,
            isAccountNonExpired = true,
            isAccountNoneLock = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        )
    }

    val loginEmail: String = "wjwan0915@gmail.com"

    val fakeJwtCreateToken = JwtCreateTokenUseCase { "token" }
//    val fakeCheckJwtToken = CheckJwtTokenUseCase { "wjwan0915@gmail.com" }

    val fakeRegistUserOutPort: RegistUserOutPort = RegistUserOutPort { user: User -> user }

    val fakeFindUserOutPort: FindUserOutPort = object : FindUserOutPort {
        override fun findByEmail(email: String): User = User(
            UUID.randomUUID(),
            UserAccount(email, "A1234567#", "망주"),
            bio = "",
            image = null,
            isAccountNonExpired = true,
            isAccountNoneLock = true,
            isCredentialsNonExpired = true,
            isEnabled = true
        )
    }

    val aeS256Encoder: PassWordEncoder = PassWordEncoder(
        "0123456789abcdef",
        "junnylandauthkey1234567891234512"
    )

}