package nabed.apps.services.utils.cachingUtils

enum class AppLanguage(val localValue: String) {
    ARABIC("ar"),
    ENGLISH("en");


    companion object {

        fun getCurrentLanguage(localString: String): AppLanguage {
            for (item in values()) {
                if (localString == item.localValue) {
                    return item
                }
            }

            //return ARABIC
            return ENGLISH
        }
    }
}