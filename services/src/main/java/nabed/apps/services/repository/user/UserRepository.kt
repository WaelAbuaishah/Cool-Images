package nabed.apps.services.repository.user


import nabed.apps.services.common.toUser
import nabed.apps.services.model.users.RoomUser
import nabed.apps.services.model.users.User
import nabed.apps.services.model.users.UserDao
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.retrofit.utils.ResponseHandler
import java.lang.Exception


class UserRepository(
    private val local: UserDao,
    private val responseHandler: ResponseHandler
) {

    suspend fun loginUser(
        username: String,
        password: String
    ) : Resource<User> {
        return try {
            val users :User =local.getUser(username, password).toUser
            responseHandler.handleSuccess(users)
        } catch (e: Exception) {
            e.printStackTrace()
            println("UserRepository.loginUser : ${e.message}")
            responseHandler.handleException(e)
        }
    }

    suspend fun registerNewUser(
        username: String,
        password: String
    ) : Resource<Long> {
        return try {
            val newUser = RoomUser(username = username, password = password)
            val users :Long =local.addNewUser(newUser)
            println("UserRepository.registerNewUser : $users")
            responseHandler.handleSuccess(users)
        } catch (e: Exception) {
            e.printStackTrace()
            responseHandler.handleException(e)
        }
    }
}