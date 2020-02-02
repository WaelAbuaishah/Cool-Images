package com.example.coolimages.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.coolimages.R

object ImageBindingAdapter {

    @JvmStatic
    @BindingAdapter("android:src")
    fun setImageUrl(view: ImageView, url: String) {
        val requestOptions = RequestOptions()

        requestOptions.placeholder(R.drawable.placeholder)
        requestOptions.error(R.drawable.error_icon)

        Glide.with(view.context).load(url).apply(requestOptions).into(view)
    }
}