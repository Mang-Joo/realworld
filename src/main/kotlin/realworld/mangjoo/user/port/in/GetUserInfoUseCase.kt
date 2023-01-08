package realworld.mangjoo.user.port.`in`

import realworld.mangjoo.user.domain.User

fun interface GetUserInfoUseCase {
    fun getUserInfo(token: String): User?


    class GetUserInfo : GetUserInfoUseCase {
        override fun getUserInfo(token: String): User? {



            return null
        }
    }
}