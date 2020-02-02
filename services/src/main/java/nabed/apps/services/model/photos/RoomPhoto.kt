package nabed.apps.services.model.photos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "photos")
data class RoomPhoto(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,

    @ColumnInfo(name = "image_height")
    val imageHeight: Int,

    @ColumnInfo(name = "image_size")
    val imageSize: Int,

    @ColumnInfo(name = "image_width")
    val imageWidth: Int,

    @ColumnInfo(name = "large_image_url")
    val largeImageURL: String,

    @ColumnInfo(name = "likes")
    val likes: Int,

    @ColumnInfo(name = "page_url")
    val pageURL: String,

    @ColumnInfo(name = "preview_height")
    val previewHeight: Int,

    @ColumnInfo(name = "preview_url")
    val previewURL: String,

    @ColumnInfo(name = "preview_width")
    val previewWidth: Int,

    @ColumnInfo(name = "user")
    val user:String,

    @ColumnInfo(name = "views")
    val views: Int
)