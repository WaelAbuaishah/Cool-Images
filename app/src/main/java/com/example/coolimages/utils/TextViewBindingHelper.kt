package com.example.coolimages.utils

import android.view.View
import androidx.databinding.BindingAdapter
import nabed.apps.services.model.photos.Photo


object TextViewBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, photo: Photo) {

        view.visibility = if (photo.user == null) View.INVISIBLE else View.VISIBLE
    }

}