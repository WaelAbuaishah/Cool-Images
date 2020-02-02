package com.example.coolimages.view.gallery

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coolimages.R
import com.example.coolimages.databinding.ViewGalleryBinding
import com.example.coolimages.view.base.ALayoutableView
import com.example.coolimages.view.base.BaseView
import com.example.coolimages.view.gallery.uiUtils.GalleryRecyclerViewAdapter
import kotlinx.android.synthetic.main.view_gallery.*
import nabed.apps.services.model.photos.Photo
import nabed.apps.services.retrofit.model.Status
import nabed.apps.services.retrofit.utils.Resource
import org.koin.androidx.viewmodel.ext.android.viewModel

@ALayoutableView(layoutId = R.layout.view_gallery)
class GalleryView : BaseView() {
    private val galleryViewModel: GalleryViewModel by viewModel()
    private var adapter: GalleryRecyclerViewAdapter? = null
    private var binding: ViewGalleryBinding? = null


    private val observer = Observer<Resource<List<Photo>>> {
        when (it.status) {
            Status.SUCCESS -> {
                println("GalleryView.Status.success")
                bindAdapter(it.data)
            }

            Status.ERROR -> {
                println("GalleryView.Status.ERROR")
            }

            Status.LOADING -> {
                println("GalleryView.Status.LOADING")
            }
        }
    }

    private fun bindAdapter(data: List<Photo>?) {
        galleryRecyclerView.visibility = View.VISIBLE

        adapter = GalleryRecyclerViewAdapter(data as ArrayList<Photo>)
        galleryRecyclerView.setHasFixedSize(true)
        galleryRecyclerView?.layoutManager = LinearLayoutManager(context!!)
        galleryRecyclerView?.itemAnimator = null
        binding?.galleryRecyclerViewAdapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (this.binding == null) {
            this.binding = baseViewBinding as ViewGalleryBinding
            galleryViewModel.getPhotos().observe(viewLifecycleOwner, observer)
        }
    }

}