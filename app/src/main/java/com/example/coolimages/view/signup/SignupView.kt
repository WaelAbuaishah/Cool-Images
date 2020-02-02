package com.example.coolimages.view.signup

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import com.example.coolimages.R
import com.example.coolimages.databinding.ViewLoginBinding
import com.example.coolimages.databinding.ViewSignUpBinding
import com.example.coolimages.utils.hideKeyboard
import com.example.coolimages.utils.onDone
import com.example.coolimages.view.base.ALayoutableView
import com.example.coolimages.view.base.BaseView
import kotlinx.android.synthetic.main.view_sign_up.*
import nabed.apps.services.retrofit.model.Status
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils
import org.koin.androidx.viewmodel.ext.android.viewModel

@ALayoutableView(layoutId = R.layout.view_sign_up)
class SignupView : BaseView() {
    private val signupViewModel: SignupViewModel by viewModel()
    private lateinit var viewSignUpBinding: ViewSignUpBinding

    private val observer = Observer<Resource<Boolean>> {
        when (it.status) {
            Status.SUCCESS -> {
                hideKeyboard()
                Navigation.findNavController(this.view!!)
                    .navigate(R.id.action_signupView_to_galleryFragment)
            }
            Status.ERROR -> {
                showErrorMessage(it.message)
            }
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewSignUpBinding= baseViewBinding as ViewSignUpBinding
        this.viewSignUpBinding.myInterface = this
        password.onDone { registerUser() }
    }


    fun registerUser() {
        signupViewModel.registerNewUser(
            username = username.text.toString(),
            password = password.text.toString()
        ).observe(viewLifecycleOwner, this.observer)
    }

}