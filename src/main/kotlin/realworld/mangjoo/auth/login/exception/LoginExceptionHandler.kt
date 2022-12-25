package realworld.mangjoo.auth.login.exception

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class LoginExceptionHandler {
    val logger = LoggerFactory.getLogger("Validation")!!

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validationException(
        ex: MethodArgumentNotValidException,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<LoginErrorResponse> {
        logger.warn("Validation 에러 발생 url:{}, trace:{}", httpServletRequest.requestURI, ex.stackTrace)
        val errorResponse = makeErrorResponse(ex.bindingResult)
        return ResponseEntity<LoginErrorResponse>(errorResponse, HttpStatus.BAD_REQUEST)
    }

    private fun makeErrorResponse(bindingResult: BindingResult): LoginErrorResponse {
        var code: Int = 0
        var description: String = ""
        var detail: String

        if (bindingResult.hasErrors()) {
            when (bindingResult.getFieldError()?.code) {
                "NotNull" -> {
                    code = LoginErrorCode.EMAIL.errorCode
                    description = LoginErrorCode.EMAIL.message
                }

                "Email" -> {
                    code = LoginErrorCode.EMAIL.errorCode
                    description = LoginErrorCode.EMAIL.message
                }
            }
        }
        return LoginErrorResponse(code, description, bindingResult.fieldError?.defaultMessage)
    }
}