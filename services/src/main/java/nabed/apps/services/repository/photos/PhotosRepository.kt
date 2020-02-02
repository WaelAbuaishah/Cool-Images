package nabed.apps.services.repository.photos


import nabed.apps.services.BuildConfig
import nabed.apps.services.common.toPhotosListFromRoomPhotos
import nabed.apps.services.common.toPhotosRoomListFromPhotos
import nabed.apps.services.model.photos.Photo
import nabed.apps.services.model.photos.PhotoDao
import nabed.apps.services.retrofit.config.ApiServices
import nabed.apps.services.retrofit.utils.Resource
import nabed.apps.services.retrofit.utils.ResponseHandler


class PhotosRepository(
    private val local: PhotoDao,
    private val apiService: ApiServices,
    private val responseHandler: ResponseHandler
) {


    suspend fun getPhotos(): Resource<List<Photo>> {
        try {


            println("PhotosRepository.getPhotos")
            val response = apiService.getPhotos(BuildConfig.API_KEY)
            println("PhotosRepository.getPhotos")

            return if (response.hits != null) {
                println("PhotosRepository.getPhotos  hits found")
                try {
                    local.insertAll(response.hits.toPhotosRoomListFromPhotos())
                    responseHandler.handleSuccess(response.hits)

                } catch (e: Exception) {
                    println("PhotosRepository.getPhotos crashed")
                    handleError(e)
                }

            } else {
                println("PhotosRepository.getPhotos data not found")
                handleError(Exception("Data not found"))
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return handleError(Exception(e))
        }
    }

    private suspend fun handleError(e: Exception): Resource<List<Photo>> {
        println("PhotosRepository.handleError")
        return try {
            val localPhotos: List<Photo> = local.getPhotos().toPhotosListFromRoomPhotos()
            if (localPhotos != null && localPhotos.isNotEmpty()) {
                responseHandler.handleSuccess(localPhotos)
            } else {
                responseHandler.handleException(e)
            }
        } catch (ex: Exception) {
            ex.printStackTrace()
            responseHandler.handleException(e)
        }
    }
}