package realworld.mangjoo.auth.config

import org.slf4j.LoggerFactory.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets.UTF_8
import java.util.*
import java.util.Base64.getDecoder
import java.util.Base64.getEncoder
import javax.crypto.Cipher
import javax.crypto.Cipher.DECRYPT_MODE
import javax.crypto.Cipher.ENCRYPT_MODE
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

const val PADDING: String = "AES/CBC/PKCS5Padding"

@Component
class PassWordEncoder(
    @Value("\${aes.iv}") private val iv: String,
    @Value("\${aes.auth}") private val auth: String
) {
    private val keySpec: SecretKeySpec get() = SecretKeySpec(auth.toByteArray(), "AES")
    private val ivSpec: IvParameterSpec get() = IvParameterSpec(iv.toByteArray())

    fun encryptAES256(text: String): String = Cipher.getInstance(PADDING)
        .also { it.init(ENCRYPT_MODE, keySpec, ivSpec) }
        .let { getEncoder().encodeToString(it.doFinal(text.toByteArray(UTF_8))) }

    fun decryptAES256(cipherText: String): String = Cipher.getInstance(PADDING)
        .also { it.init(DECRYPT_MODE, keySpec, ivSpec) }
        .let { String(it.doFinal(getDecoder().decode(cipherText)), UTF_8) }

}