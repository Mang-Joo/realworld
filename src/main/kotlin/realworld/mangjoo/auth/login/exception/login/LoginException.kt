package realworld.mangjoo.auth.login.exception.login

class LoginException(val header: String, message: String?) : IllegalStateException(message) {
}