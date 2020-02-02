package com.example.coolimages.view.gallery.uiUtils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.coolimages.R
import com.example.coolimages.databinding.RecyclerItemGalleryLayoutBinding
import nabed.apps.services.model.photos.Photo

class GalleryRecyclerViewAdapter(
    var data: ArrayList<Photo>
) : RecyclerView.Adapter<GalleryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding: RecyclerItemGalleryLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.recycler_item_gallery_layout, parent, false
        )

        return GalleryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.onBind(data[position])
    }

}