package nabed.apps.services.retrofit.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


class ErrorResponse(
    @SerializedName("title") val title: String,
    @SerializedName("status") val status: Int,
    @SerializedName("detail", alternate = ["error"]) var detail: String,
    @SerializedName("message") var message: String,
    @SerializedName("validation_messages") var validationMessageObject: JsonObject?
) {
    var validationMessages: ValidationMessages? = null
    var totalMsg: String = ""

    /* {
        "type":"http://www.w3.org/Protocols/rfc2616/rfc2616-sec10.html",
        "title":"invalid_grant",
        "status":400,
        "detail":"refresh_token doesn't exist or is invalid for the client"
    } */
}