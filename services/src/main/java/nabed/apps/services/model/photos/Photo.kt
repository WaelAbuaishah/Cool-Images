package nabed.apps.services.model.photos

import java.io.Serializable

data class Photo(
    val id: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val imageWidth: Int,
    val largeImageURL: String,
    val likes: Int,
    val pageURL: String,
    val previewHeight: Int,
    val previewURL: String,
    val previewWidth: Int,
    val user:String,
    val views: Int
):Serializable