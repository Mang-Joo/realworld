package realworld.mangjoo.auth.login.exception

enum class LoginErrorCode(
    val errorCode: Int,
    val message: String
) {
    NOT_NULL(1001, "A required value is missing."),
    EMAIL(1002, "The format is not valid."),


}