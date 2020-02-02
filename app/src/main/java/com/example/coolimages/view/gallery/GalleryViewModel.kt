package com.example.coolimages.view.gallery

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import nabed.apps.services.model.photos.Photo
import nabed.apps.services.repository.photos.PhotosRepository
import nabed.apps.services.retrofit.utils.Resource

class GalleryViewModel (private val photosRepository: PhotosRepository) : ViewModel() {

    fun getPhotos(): LiveData<Resource<List<Photo>>> {
        return liveData(Dispatchers.IO) {
            emit(Resource.loading(null))
            val response = photosRepository.getPhotos()
            emit(response)
        }
    }
}