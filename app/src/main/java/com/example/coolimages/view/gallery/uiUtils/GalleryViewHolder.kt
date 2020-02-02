package com.example.coolimages.view.gallery.uiUtils

import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.coolimages.R
import com.example.coolimages.databinding.RecyclerItemGalleryLayoutBinding
import nabed.apps.services.model.photos.Photo

class GalleryViewHolder(
    private var viewBinder: RecyclerItemGalleryLayoutBinding
) : RecyclerView.ViewHolder(viewBinder.root) {
    fun onBind(
        photo: Photo) {
        viewBinder.photoItem = photo
        viewBinder.holderItem = this
        viewBinder.executePendingBindings()
    }

    fun onItemClicked(view: View, photo: Photo) {
        println("GalleryViewHolder.onClicked: ")
        val bundle = bundleOf("photo" to photo)
        Navigation.findNavController(view)
            .navigate(R.id.action_galleryFragment_to_galleryDetailsView, bundle)
    }
}