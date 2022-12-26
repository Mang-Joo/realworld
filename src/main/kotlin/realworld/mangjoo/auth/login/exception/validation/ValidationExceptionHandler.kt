package realworld.mangjoo.auth.login.exception.validation

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import realworld.mangjoo.auth.login.exception.ErrorResponse
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class ValidationExceptionHandler {
    val logger = LoggerFactory.getLogger("Validation")!!

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun validationException(ex: MethodArgumentNotValidException, httpServletRequest: HttpServletRequest): ResponseEntity<ErrorResponse> {
        logger.warn("Validation 에러 발생 url:{}, trace:{}", httpServletRequest.requestURI, ex.stackTrace)
        return ResponseEntity<ErrorResponse>(makeErrorResponse(ex.bindingResult, ex.message), HttpStatus.BAD_REQUEST)
    }

    private fun makeErrorResponse(bindingResult: BindingResult, message: String): ErrorResponse =
        when (bindingResult.fieldError?.code) {
            "NotBlank" -> ErrorResponse(ValidationErrorCode.NOT_BLANK.errorCode, bindingResult.fieldError?.defaultMessage)
            "Pattern" -> ErrorResponse(ValidationErrorCode.PATTERN.errorCode, bindingResult.fieldError?.defaultMessage)
            else -> ErrorResponse(0, message)
        }
}