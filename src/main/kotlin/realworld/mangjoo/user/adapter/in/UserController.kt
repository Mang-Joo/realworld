package realworld.mangjoo.user.adapter.`in`

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import realworld.mangjoo.auth.config.AES256EncryptionDecryption
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterRequestDto
import realworld.mangjoo.user.adapter.`in`.dto.UserRegisterResponse
import realworld.mangjoo.user.port.`in`.UserRegistrationUseCase

@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRegistrationUseCase: UserRegistrationUseCase,
    private val aeS256EncryptionDecryption: AES256EncryptionDecryption
) {

    @PostMapping
    fun registerUser(@RequestBody userRegisterRequestDto: UserRegisterRequestDto): ResponseEntity<UserRegisterResponse> {
        val userAccount = UserRegisterRequestDto.convertDtoToDomainByEncrypt(userRegisterRequestDto, aeS256EncryptionDecryption.encryptAES256(userRegisterRequestDto.password))
        val user = userRegistrationUseCase.registration(userAccount)
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(user)
    }

}