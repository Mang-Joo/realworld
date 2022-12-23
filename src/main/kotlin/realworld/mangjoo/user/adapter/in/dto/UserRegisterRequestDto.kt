package realworld.mangjoo.user.adapter.`in`.dto

import org.springframework.security.crypto.password.PasswordEncoder
import realworld.mangjoo.user.domain.UserAccount

data class UserRegisterRequestDto(
    val email: String,
    val password: String,
    val username: String
) {
    companion object{
        fun convertDtoToDomain(userRegisterRequestDto: UserRegisterRequestDto, passwordEncoder: PasswordEncoder): UserAccount {

            return UserAccount(
                userRegisterRequestDto.email,
                passwordEncoder.encode(userRegisterRequestDto.password),
                userRegisterRequestDto.username)
        }
    }
}

