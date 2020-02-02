package com.example.coolimages.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.coolimages.R
import com.example.coolimages.databinding.ViewLoginBinding
import com.example.coolimages.utils.hideKeyboard
import com.example.coolimages.utils.onDone
import com.example.coolimages.view.base.ALayoutableView
import com.example.coolimages.view.base.BaseView
import kotlinx.android.synthetic.main.view_login.*
import nabed.apps.services.model.users.User
import nabed.apps.services.retrofit.model.Status
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

@ALayoutableView(layoutId = R.layout.view_login)
class LoginView : BaseView() {
    private val loginViewModel: LoginViewModel by viewModel()
    lateinit var viewLoginBinding: ViewLoginBinding

    private val observer = Observer<Resource<User>> {
        when (it.status) {
            Status.SUCCESS -> {
                hideKeyboard()
                Navigation.findNavController(this.view!!)
                    .navigate(R.id.action_loginFragment_to_galleryFragment)
            }
            Status.ERROR -> {
                showErrorMessage(it.message)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        println("LoginView.onViewCreated")
        viewLoginBinding = baseViewBinding as ViewLoginBinding
        this.viewLoginBinding.myInterface = this
        password.onDone { onLoginClicked() }
    }

    fun onSignupClicked() {
        findNavController().navigate(R.id.action_loginFragment_to_signupView)
    }

    fun onLoginClicked() {
        loginViewModel.login(
            username = username.text.toString(),
            password = password.text.toString()
        ).observe(viewLifecycleOwner, observer)
    }
}