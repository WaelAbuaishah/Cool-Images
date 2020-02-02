package com.example.coolimages.di

import com.example.coolimages.view.gallery.GalleryViewModel
import com.example.coolimages.view.login.LoginViewModel
import com.example.coolimages.view.signup.SignupViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val UserViewModelModule = module {

    viewModel { LoginViewModel(get()) }
    viewModel { SignupViewModel(get()) }
    viewModel { GalleryViewModel(get()) }
}