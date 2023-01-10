package realworld.mangjoo.auth.config

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import realworld.mangjoo.fixture.Fixture

class PassWordEncoderTest {

    private val aeS256Encoder: PassWordEncoder = Fixture().aeS256Encoder

    private val raw: String = "mangjoo"
    private val encrypted: String = "wQ0o4XschBMRL8SnJg6lRg=="

    @Test
    @DisplayName("암호화 테스트")
    fun `encrypt test`() {
        val encryptAES256 = aeS256Encoder.encryptAES256(raw)
        assertThat(encryptAES256).isEqualTo(encrypted)
    }

    @Test
    @DisplayName("복호화 테스트")
    fun `decrypt test`() {
        val decryptAES256 = aeS256Encoder.decryptAES256(encrypted)
        assertThat(decryptAES256).isEqualTo(raw)
    }
}