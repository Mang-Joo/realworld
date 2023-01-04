package realworld.mangjoo.user.adapter.`in`.dto

import realworld.mangjoo.user.domain.UserAccount
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Pattern

data class UserRegisterRequestDto(
    @Email
    val email: String,
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}\$", message = "최소 8자, 하나 이상의 문자와 하나 이상의 숫자가 필요합니다.")
    val password: String,
    @NotBlank(message = "이름은 비어 있을 수 없습니다.")
    val username: String
) {
    companion object {
        fun convertDtoToDomainByEncrypt(
            userRegisterRequestDto: UserRegisterRequestDto,
            encryptPassword:String
        ): UserAccount {

            return UserAccount(
                email = userRegisterRequestDto.email,
                passWord = encryptPassword,
                userName = userRegisterRequestDto.username
            )
        }
    }

}

