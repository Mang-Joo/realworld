package realworld.mangjoo.auth.jwt

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class JwtCreateTokenUseCaseTest{

    @Test
    @DisplayName("로그인을 하면  토큰이 발행된다.")
    fun a(){
        val createToken = JwtCreateTokenUseCase
            .JwtCreateTokenProvider("key", 3000L)
            .createToken("hello")

        println(createToken)
    }

}