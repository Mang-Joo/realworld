package realworld.mangjoo.auth.jwt

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JwtTokenUseCaseTest{

    @Test
    @DisplayName("비밀번호를 입력하면 토큰이 발행된다.")
    fun a(){
        val createToken = JwtTokenUseCase
            .JwtTokenProvider("key", 3000)
            .createToken("hello")

        println(createToken)
    }

}