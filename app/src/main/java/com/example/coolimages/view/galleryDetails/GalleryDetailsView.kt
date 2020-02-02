package com.example.coolimages.view.galleryDetails

import android.os.Bundle
import android.view.View
import com.example.coolimages.R
import com.example.coolimages.databinding.ViewGalleryDetailsBinding
import com.example.coolimages.view.base.ALayoutableView
import com.example.coolimages.view.base.BaseView
import nabed.apps.services.model.photos.Photo

@ALayoutableView(layoutId = R.layout.view_gallery_details)
class GalleryDetailsView  : BaseView() {
    private lateinit var binding: ViewGalleryDetailsBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val photo :Photo? = arguments?.get("photo") as Photo?
        this.binding = baseViewBinding as ViewGalleryDetailsBinding
        binding.photoItem = photo
        binding.executePendingBindings()
    }

}