package nabed.apps.services.retrofit.utils


import nabed.apps.services.retrofit.model.ErrorResponse
import nabed.apps.services.retrofit.model.Status

data class Resource<out T>(
    var status: Status,
    val data: T? = null,
    var message: String? = "Message is Null",
    val error: ErrorResponse? = null
) {
    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String ="",data:T?= null ): Resource<T> {
            return Resource(
                Status.ERROR,
                null,
                msg,
                null
            )
        }

        fun <T> loading(data: T?=null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
