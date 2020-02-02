package nabed.apps.services.utils.cachingUtils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager


import com.google.gson.Gson


import java.lang.reflect.Type

object SharedPreferencesUtils {

    const val FILE_NAME = "PrefFile"
    private const val IS_UPDATE_PROCESS = "is_update_process"
    private const val NOT_INITIALIZED_ERROR = "Shared Preferences is not initialized"
    private var preferences: SharedPreferences? = null
    private var instance: SharedPreferencesUtils? = null

    fun getInstance(): SharedPreferencesUtils {
        checkNotNull(instance) { NOT_INITIALIZED_ERROR }
        return instance!!
    }

    fun initializeSharedPrefsService(context: Context) {
        if (instance == null) {
            instance =
                SharedPreferencesUtils
            preferences = PreferenceManager.getDefaultSharedPreferences(context)
        }
    }

    private fun saveObjectToSharedPreference(
        serializedObjectKey: String,
        `object`: Any
    ) {
        val sharedPreferences = getInstance().preferences!!
        val sharedPreferencesEditor = sharedPreferences.edit()
        val gson = Gson()
        val serializedObject = gson.toJson(`object`)
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject)
        sharedPreferencesEditor.apply()
    }

    private fun <T> getSavedObjectFromPreference(
        preferenceKey: String,
        classType: Class<T>
    ): T? {
        val sharedPreferences = getInstance().preferences!!
        if (sharedPreferences.contains(preferenceKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType)
        }
        return null
    }



    fun getStringValue(key: String, defaultValue: String = ""): String {

        return preferences!!.getString(key, defaultValue)!!
    }

    fun putStringValue(key: String, value: String) {

        preferences!!.edit()
            .putString(key, value)
            .apply()
    }

    fun removeValue(key: String) {

        preferences!!.edit()
            .remove(key)
            .apply()
    }

    fun getBooleanValue(key: String, defaultValue: Boolean?): Boolean {

        return preferences!!.getBoolean(key, defaultValue!!)
    }

    fun putBooleanValue(key: String, value: Boolean) {

        preferences!!.edit()
            .putBoolean(key, value)
            .apply()
    }

    fun saveBooleanToSharedPreference(
        context: Context,
        fileName: String,
        serializedObjectKey: String,
        boolean: Boolean
    ) {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.putBoolean(serializedObjectKey, boolean)
        sharedPreferencesEditor.apply()
    }

    fun getBooleanFromPreference(
        context: Context,
        fileName: String,
        preferenceKey: String,
        defaultValue: Boolean
    ): Boolean {
        val sharedPreferences = context.getSharedPreferences(fileName, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            return sharedPreferences.getBoolean(preferenceKey, defaultValue)
        }
        return defaultValue
    }

    fun getIsUpdateProcess(context: Context): Boolean {
        return getBooleanFromPreference(
            context,
            FILE_NAME,
            IS_UPDATE_PROCESS,
            false
        )
    }

    fun saveIsUpdateProcess(context: Context, isUpdated: Boolean) {
        saveBooleanToSharedPreference(
            context,
            FILE_NAME,
            IS_UPDATE_PROCESS,
            isUpdated
        )
    }

    fun saveArrayOfObjectToSharedPreference(
        context: Context,
        fileName: String,
        serializedObjectKey: String,
        `object`: Any
    ) {
        val sharedPreferences = context.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val sharedPreferencesEditor = sharedPreferences.edit()
        val gson = Gson()
        val serializedObject = gson.toJson(`object`)
        sharedPreferencesEditor.putString(serializedObjectKey, serializedObject)
        sharedPreferencesEditor.apply()
    }

    fun <T> getSavedArrayOfObjectFromPreference(
        context: Context,
        fileName: String,
        preferenceKey: String,
        classType: Class<T>
    ): T? {
        val sharedPreferences = context.getSharedPreferences(fileName, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType)
        }
        return null
    }

    fun getSavedArrayOfObjectFromPreference(
        context: Context,
        fileName: String,
        preferenceKey: String,
        classType: Type
    ): ArrayList<Any>? {
        val sharedPreferences = context.getSharedPreferences(fileName, 0)
        if (sharedPreferences.contains(preferenceKey)) {
            val gson = Gson()
            return gson.fromJson(sharedPreferences.getString(preferenceKey, ""), classType)
        }
        return null
    }

    fun getIntegerValue(key: String, defaultValue: Int?): Int {

        return preferences!!.getInt(key, defaultValue!!)
    }


    fun putIntegerValue(key: String, value: Int) {

        preferences!!.edit()
            .putInt(key, value)
            .apply()
    }


    fun emptyUserCache() {
        val sharedPreferences = preferences!!
        val sharedPreferencesEditor = sharedPreferences.edit()
        sharedPreferencesEditor.apply()
        sharedPreferencesEditor.commit()
    }


    fun emptyAllCache(context: Context) {
        var pref = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE).edit()
        pref.clear()
        pref.commit()
    }
}

