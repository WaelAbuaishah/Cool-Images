package nabed.apps.services.repository.photos.response

import nabed.apps.services.model.photos.Photo

data class ImagesResponse(
    val hits: List<Photo>,
    val total: Int,
    val totalHits: Int
)