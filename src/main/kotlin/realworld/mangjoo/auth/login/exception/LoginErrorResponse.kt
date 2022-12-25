package realworld.mangjoo.auth.login.exception

data class LoginErrorResponse(
    val code: Int,
    val description: String,
    val detail: String?
)