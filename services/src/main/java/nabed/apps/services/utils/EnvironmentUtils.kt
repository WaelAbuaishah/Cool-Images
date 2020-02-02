package nabed.apps.services.utils

object EnvironmentUtils {
    var isQaEnvironmentRequired = true

    fun getBaseUrl(): String {
        return if (isQaEnvironmentRequired) {
            "http://api.nabed.xyz"
        } else {
            "https://coreapi.nabed.net"
        }
    }


    fun getFirstCollectionName(): String {
        return if (isQaEnvironmentRequired) {
            "qa-config"
        } else {
            "config"
        }
    }

    fun getDocumentName(): String {
        // it's same for both env
        return "app_configuration_document_id"
    }

}