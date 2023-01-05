package realworld.mangjoo.auth.login.adpater.`in`

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase
import realworld.mangjoo.user.domain.User
import javax.validation.Valid


const val EMAIL_ADDRESS: String = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"
const val PASSWORD: String = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,}\$"

@RestController
@RequestMapping("/api/users")
class LoginController(
    private val loginUseCase: LoginUseCase,
) {
    val log = LoggerFactory.getLogger("Login")!!

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody userLoginRequestDto: LoginRequest): ResponseEntity<LoginResponse> {
        log.info("login : {}", userLoginRequestDto)
        val loginUser: User = loginUseCase.loginUser(userLoginRequestDto)
        val userResponse = LoginResponse(loginUser)
        return ResponseEntity
            .status(200)
            .header("Token", loginUser.token)
            .body(userResponse)
    }


    data class LoginRequest(
        val email: String,
        val password: String,
    ) {
        init {
            require(email.isNotBlank()) { throw IllegalArgumentException("email is blank") }
            require(password.isNotBlank()) { throw IllegalArgumentException("password is blank") }
            require(email.matches(Regex(EMAIL_ADDRESS))) { throw IllegalArgumentException("email is not valid") }
            require(password.matches(Regex(PASSWORD))) { throw IllegalArgumentException("password is not valid") }
        }
    }

    data class LoginResponse(
        val email: String,
        val username: String,
        val bio: String,
        val image: String?,
    ) {
        constructor(user: User) : this(
            user.userAccount.email,
            user.userAccount.userName,
            user.bio,
            user.image,
        )
    }
}