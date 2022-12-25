package realworld.mangjoo.auth.login.adpater.`in`

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
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
    fun `이메일 벨리데이션 테스트`() {
        val uri: String = "/api/users/login"

        val userLoginDto = UserLoginDto("fwaopj", "awdjpoawdpawjp1234")
        val userDtoJson = jacksonObjectMapper().writeValueAsString(userLoginDto)

        mockMvc.perform(
            post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJson)
        )
            .andExpect(status().isBadRequest)
            .andDo(print())

    }
}