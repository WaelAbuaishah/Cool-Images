package com.example.coolimages.view.signup

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import nabed.apps.services.repository.user.UserRepository
import nabed.apps.services.retrofit.model.Status
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils

class SignupViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun registerNewUser(username: String, password: String): LiveData<Resource<Boolean>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val validationMessage = validateParams(username, password)
            if (validationMessage == null) {
                val response = userRepository.registerNewUser(username, password)
                if (response.data != null &&
                    response.data as Long > 0
                ) {
                    SharedPreferencesUtils.getInstance().putBooleanValue("Loggedin",true)
                    emit(Resource.success(true))
                } else {
                    emit(Resource.error(response.message!!, false))
                }
            } else {
                emit(Resource(Status.ERROR, null, validationMessage,null))
            }
        }
    }

    private fun validateParams(username: String, password: String): String? {
        return if (username.length < 6 || username.contains(" ") || password.length < 6 || password.contains(" ")) {
            "Invalid input data\n- Username andPassword should have at least 6 characters\n- No spaces allowed"
        } else {
            null
        }
    }
}