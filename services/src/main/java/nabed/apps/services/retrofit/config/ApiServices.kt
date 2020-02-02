package nabed.apps.services.retrofit.config

import nabed.apps.services.repository.photos.response.ImagesResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiServices {

    @GET("api/")
    suspend fun getPhotos(@Query("key") keyValue:String): ImagesResponse
}