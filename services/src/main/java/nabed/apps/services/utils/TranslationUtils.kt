package nabed.apps.services.utils

import android.content.Context
import nabed.apps.services.R
import org.koin.core.KoinComponent
import org.koin.core.inject
import org.koin.core.parameter.parametersOf

object TranslationUtils : KoinComponent {
    private val context: Context by inject { parametersOf() }

    fun getErrorTranslation(error: String?): String {
        if (error.isNullOrEmpty()) {
            return context.getString(R.string.unExpectedErrorMessage)
        }

        when {
            error.contains("UNIQUE constraint failed: users.id", true) -> {
                return context.getString(R.string.userAlreadyExist)
            }

            error.contains("Parameter specified as non-null is null", true) -> {
                return context.getString(R.string.userNotFound)
            }

            error.contains("SSL handshake") ||
                    error.contains(
                        "failed to connect",
                        true
                    ) ||
                    error.contains("timeout", true)||
                    error.contains("UnknownHostException", true)
            -> {
                return context.getString(R.string.slowInternetConnection)
            }

        }
        return error
    }

}