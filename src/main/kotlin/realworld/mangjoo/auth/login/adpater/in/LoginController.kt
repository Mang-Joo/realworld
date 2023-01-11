package realworld.mangjoo.auth.login.adpater.`in`

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase.*
import realworld.mangjoo.user.adapter.`in`.dto.PASSWORD
import realworld.mangjoo.user.domain.User
import javax.validation.Valid


const val EMAIL_ADDRESS: String = "^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"

@RestController
@RequestMapping("/api/users")
class LoginController(
    private val loginUseCase: LoginUseCase,
) {
    val log = LoggerFactory.getLogger("Login")!!

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody userLoginRequestDto: LoginRequest): ResponseEntity<UserResponse> =
        loginUseCase
            .loginUser(userLoginRequestDto)
            .also { log.info("login : {}", userLoginRequestDto.email) }
            .let { ResponseEntity.status(200).body(it) }


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

        fun encryptPassword(password: String) = LoginUseCaseRequest(email, password)
    }

    data class UserResponse(
        val email: String,
        val token: String,
        val username: String,
        val bio: String,
        val image: String?,
    ) {
        constructor(user: User, token: String) : this(
            user.userAccount.email,
            token,
            user.userAccount.userName,
            user.bio,
            user.image,
        )
    }
}