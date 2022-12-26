package realworld.mangjoo.auth.login.adpater.`in`

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import realworld.mangjoo.auth.login.adpater.`in`.dto.UserLoginDto

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(controllers = [LoginController::class])
class LoginControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun `로그인 테스트`() {
        val uri: String = "/api/users/login"

        val userLoginDto = UserLoginDto("lsun606@naver.com", "Aaqpa#lzm13$")
        val userDtoJson = jacksonObjectMapper().writeValueAsString(userLoginDto)

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)
        )
            .andExpect(status().isUnauthorized)
            .andDo(print())

    }
    @Test
    @DisplayName("이메일이 비어있으면 에러가 발생한다.")
    fun `email empty validation exception test`() {
        val uri: String = "/api/users/login"

        val userDtoJson: String = "{\"email\":\" \",\"password\":\"Aaqpa#lzm13\$\"}"

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

    }

    @Test
    @DisplayName("이메일 형식이 안맞으면 에러가 발생한다.")
    fun `email form validation exception test`() {
        val uri: String = "/api/users/login"

        val userDto: String = "{\"email\":\"lsun606\",\"password\":\"Aaqpa#lzm13\$\"}"

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDto)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

    }
}