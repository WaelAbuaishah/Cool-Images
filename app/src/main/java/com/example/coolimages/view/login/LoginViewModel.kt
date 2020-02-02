package com.example.coolimages.view.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import nabed.apps.services.model.users.User
import nabed.apps.services.repository.user.UserRepository
import nabed.apps.services.retrofit.model.Status
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils

class LoginViewModel(private val userRepository: UserRepository) : ViewModel() {

    fun login(username: String, password: String): LiveData<Resource<User>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val validationMessage = validateParams(username, password)
            if (validationMessage == null) {
                SharedPreferencesUtils.getInstance().putBooleanValue("Loggedin",true)
                val response = userRepository.loginUser(username, password)
                emit(response)
            } else {
                emit(Resource(Status.ERROR, null, validationMessage,null))
            }
        }
    }

    private fun validateParams(username: String, password: String): String? {
        return if (username.isNullOrEmpty() || password.isNullOrEmpty()) {
            "Invalid input data"
        } else {
            null
        }
    }
}