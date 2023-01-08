package realworld.mangjoo.user.adapter.`in`

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import realworld.mangjoo.auth.config.AES256Encoder
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterRequest
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.port.`in`.UserRegistrationUseCase

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val aeS256Encoder: AES256Encoder
) {

    @PostMapping
    fun registerUser(@RequestBody userRegisterRequest: UserRegisterRequest): ResponseEntity<UserRegisterResponse> =
        userRegistrationUseCase
            .registration(userRegisterRequest.encryptPasswordToUserAccount(aeS256Encoder.encryptAES256(userRegisterRequest.password)))
            .let {
                ResponseEntity.status(HttpStatus.CREATED).body(it)
            }

//    @GetMapping
//    fun loginUserStatus(@RequestHeader token: String): ResponseEntity<LoginController.UserResponse> {
//
//    }

}