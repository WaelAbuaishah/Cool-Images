package nabed.apps.services.retrofit.utils

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParseException
import nabed.apps.services.retrofit.model.ErrorResponse
import nabed.apps.services.retrofit.model.ValidationMessage
import nabed.apps.services.retrofit.model.ValidationMessages
import nabed.apps.services.utils.TranslationUtils
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

enum class ErrorCodes(val code: Int) {
    SocketTimeOut(-1),
    NoInternetConnection(0)
}

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T> {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T> {
        return when (e) {
            is HttpException -> {
                handleHttpException(e.response()?.errorBody(), e.code())
            }
            is SocketTimeoutException -> Resource.error(
                getErrorMessage(
                    ErrorCodes.SocketTimeOut.code
                ), null
            )
            is UnknownHostException -> Resource.error(
                TranslationUtils.getErrorTranslation(e.message), null
            )
            else -> Resource.error(
                e.message!!,
                null
            )
        }
    }

    private fun getErrorMessage(code: Int): String {
        return when (code) {
            ErrorCodes.SocketTimeOut.code -> "Timeout"
            ErrorCodes.NoInternetConnection.code -> "Timeout"
            401 -> "Unauthorised"
            404 -> "Not found"
            else -> "Something went wrong"
        }
    }


    private fun <T> handleHttpException(response: ResponseBody?, code: Int): Resource<T> {

        try {
            val gson = Gson()
            val error =
                gson.fromJson(response!!.charStream(), ErrorResponse::class.java)
            if (error?.validationMessageObject != null)
                error.validationMessages = deserialize(error.validationMessageObject!!)
            if (error != null) {
                if (error.detail != null)
                    error.detail = TranslationUtils.getErrorTranslation(error.detail)
                if (error.message != null)
                    error.message = TranslationUtils.getErrorTranslation(error.message)
            }

            return Resource.error(
                error.totalMsg ?: error.message
            )
        } catch (e: Exception) {
            e.printStackTrace()
            return Resource.error(e.message!!, null)
        }
    }

    @Throws(JsonParseException::class)
    fun deserialize(json: JsonObject): ValidationMessages {

        val entrySet = json.entrySet()

        val parsedMessages = ArrayList<ValidationMessage>()
        for ((key, value) in entrySet) {
            val validation = parseValidationMessage(value, key)
            parsedMessages.add(validation)
        }
        parsedMessages.size

        return ValidationMessages(parsedMessages)
    }

    private fun parseValidationMessage(
        validation: JsonElement,
        property: String
    ): ValidationMessage {
        val validationMessage = ValidationMessage()
        validationMessage.property = property

        val validationObject = validation.asJsonObject
        val validationObjectEntrySet = validationObject.entrySet()
        for ((title, value) in validationObjectEntrySet) {
            validationMessage.title = title
            val message = value.asString
            validationMessage.message = message
        }

        return validationMessage
    }

}
