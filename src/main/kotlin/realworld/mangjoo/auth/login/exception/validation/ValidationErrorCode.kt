package realworld.mangjoo.auth.login.exception.validation

enum class ValidationErrorCode(val errorCode: Int) {
    NOT_BLANK(1001),
    PATTERN(1002),
}