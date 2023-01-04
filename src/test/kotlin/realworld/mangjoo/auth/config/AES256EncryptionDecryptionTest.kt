package realworld.mangjoo.auth.config

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class AES256EncryptionDecryptionTest {

    private val aeS256EncryptionDecryption: AES256EncryptionDecryption = AES256EncryptionDecryption(
        "0123456789abcdef",
        "junnylandauthkey1234567891234512"
    )

    private val raw: String = "mangjoo"
    private val encrypted: String = "HPqyK6PHQYFWU2CrbVlhnw=="

    @Test
    @DisplayName("암호화 테스트")
    fun `encrypt test`() {
        val encryptAES256 = aeS256EncryptionDecryption.encryptAES256(raw)
        println("encrypted = ${encrypted}")
        assertThat(encryptAES256).isEqualTo(encryptAES256)
    }

    @Test
    @DisplayName("복호화 테스트")
    fun `decrypt test`() {
        val decryptAES256 = aeS256EncryptionDecryption.decryptAES256(encrypted)
        assertThat(decryptAES256).isEqualTo(raw)
    }
}