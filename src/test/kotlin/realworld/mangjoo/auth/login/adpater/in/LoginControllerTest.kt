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

        val userLoginDto = UserLoginDto("lsun606@naver.com", "Aaqpalzm13$")
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
    @DisplayName("이메일 에러 테스트.")
    fun `email exception test`() {
        val uri: String = "/api/users/login"

        val emailNullJson: String = "{\"email\":\" \",\"password\":\"비밀번호\$\"}"
        val noTypeEmailJson: String = "{\"email\":\"wjwan0915\",\"password\":\"비밀번호\$\"}"

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(emailNullJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(noTypeEmailJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
    }

    @Test
    @DisplayName("비밀번호 에러 테스트")
    fun `password exception test`() {
        val uri: String = "/api/users/login"

        val nullPasswordJson: String = "{\"email\":\"wjwan0915@gmail.com\",\"password\":\"\$\"}"
        val noTypePasswordJson: String = "{\"email\":\"wjwan0915@gmail.com\",\"password\":\"1234\$\"}"

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(noTypePasswordJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(nullPasswordJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())
    }
}