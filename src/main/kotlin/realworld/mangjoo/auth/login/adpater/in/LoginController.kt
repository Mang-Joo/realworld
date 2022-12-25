package realworld.mangjoo.auth.login.adpater.`in`

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginResponse
import realworld.mangjoo.auth.login.port.`in`.LoginUseCase
import realworld.mangjoo.user.domain.User
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class LoginController(
    private val loginUseCase: LoginUseCase
) {
    val log = LoggerFactory.getLogger("Login")!!

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody userLoginRequestDto: UserLoginDto): ResponseEntity<UserLoginResponse> {
        log.info("login : {}", userLoginRequestDto)
        val loginUser: User = loginUseCase.loginUser(userLoginRequestDto)
        val userResponse = UserLoginResponse.convertDomainToDto(loginUser)

        return ResponseEntity
            .status(200)
            .header("Token", loginUser.token)
            .body(userResponse)
    }
}