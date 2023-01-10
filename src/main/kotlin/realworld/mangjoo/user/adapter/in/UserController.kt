package realworld.mangjoo.user.adapter.`in`

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import realworld.mangjoo.auth.config.PassWordEncoder
import realworld.mangjoo.auth.login.adpater.`in`.LoginController.*
import realworld.mangjoo.user.adapter.`in`.dto.UpdateUserRequest
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterRequest
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.port.`in`.GetUserInfoUseCase
import realworld.mangjoo.user.port.`in`.RegistUserUseCase
import realworld.mangjoo.user.port.`in`.UpdateUserUseCase

@RestController
@RequestMapping("/api/users")
class UserController(
    private val registUserUseCase: RegistUserUseCase,
    private val getUserInfoUseCase: GetUserInfoUseCase,
    private val updateUserUseCase: UpdateUserUseCase,
    private val aeS256Encoder: PassWordEncoder
) {

    @PostMapping
    fun registerUser(@RequestBody userRegisterRequest: UserRegisterRequest): ResponseEntity<UserRegisterResponse> =
        userRegisterRequest.encryptPasswordToUserAccount(aeS256Encoder.encryptAES256(userRegisterRequest.password))
            .run { registUserUseCase.registration(this) }
            .let { ResponseEntity.status(HttpStatus.CREATED).body(it) }

    @GetMapping
    fun loginUserStatus(@RequestHeader token: String): ResponseEntity<UserResponse> =
        getUserInfoUseCase.getUserInfo(token)
            .run { UserResponse(this, token) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }

    @PutMapping
    fun updateUser(
        @RequestHeader token: String,
        @RequestBody updateUserRequest: UpdateUserRequest
    ): ResponseEntity<UserResponse> =
        updateUserUseCase.updateUser(
            token,
            updateUserRequest.toDomainUserAccount(),
            updateBio = updateUserRequest.bio,
            updateImage = updateUserRequest.image
        )
            .run { UserResponse(this, token) }
            .let { ResponseEntity.status(HttpStatus.OK).body(it) }
}