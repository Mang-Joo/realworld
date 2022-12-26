package realworld.mangjoo.auth.login.exception.login

import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import realworld.mangjoo.auth.login.exception.ErrorResponse
import realworld.mangjoo.auth.login.exception.validation.ValidationErrorCode
import javax.servlet.http.HttpServletRequest

@RestControllerAdvice
class LoginExceptionHandler {
    val logger = LoggerFactory.getLogger("Login")!!

    @ExceptionHandler(value = [LoginException::class])
    fun validationException(
        ex: LoginException,
        httpServletRequest: HttpServletRequest
    ): ResponseEntity<ErrorResponse> {
        logger.warn("로그인 에러 발생 url:{}, trace:{}", httpServletRequest.requestURI, ex.stackTrace)
        val errorResponse = makeErrorResponse(ex)
        return ResponseEntity<ErrorResponse>(errorResponse, HttpStatus.UNAUTHORIZED)
    }

    private fun makeErrorResponse(loginException: LoginException): ErrorResponse {
        var code: Int = 0

        when (loginException.header) {
            "CanNotLogin" -> code = LoginErrorCode.CANNOTLOGIN.errorCode
        }

        return ErrorResponse(code, loginException.message)
    }

    private fun makeErrorResponse(bindingResult: BindingResult, message: String): ErrorResponse =
        when (bindingResult.fieldError?.code) {
            "NotBlank" -> ErrorResponse(ValidationErrorCode.NOT_BLANK.errorCode, bindingResult.fieldError?.defaultMessage)
            "Pattern" -> ErrorResponse(ValidationErrorCode.PATTERN.errorCode, bindingResult.fieldError?.defaultMessage)
            else -> ErrorResponse(0, message)
        }
}