package realworld.mangjoo.auth.login.adpater.`in`.dto

import realworld.mangjoo.user.domain.User
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern


const val EMAIL_ADDRESS: String = "^[a-zA-Z0-9+-\\_.]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+\$"

const val PASSWORD: String = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[\$@!%*#?&])[A-Za-z\\d\$@!%*#?&]{8,}\$"

data class UserLoginDto(
    @field:Pattern(regexp = EMAIL_ADDRESS, message = "이메일 형식에 맞지 않습니다.")
    @field:NotBlank(message = "빈 값은 입력할 수 없습니다.")
    val email: String,
    @field:Pattern(regexp = PASSWORD, message = "비밀번호 형식에 맞지 않습니다.")
    @field:NotBlank(message = "빈 값은 입력할 수 없습니다.")
    val password: String
)

data class UserLoginResponse(
    val email: String,
    val username: String,
    val bio: String,
    val image: String?
) {
    companion object {
        fun convertDomainToDto(user: User): UserLoginResponse {
            return UserLoginResponse(
                user.userAccount.email,
                user.userAccount.userName,
                user.bio,
                user.image
            )
        }
    }
}
