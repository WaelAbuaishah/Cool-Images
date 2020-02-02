package nabed.apps.services.common

import nabed.apps.services.model.photos.Photo
import nabed.apps.services.model.photos.RoomPhoto
import nabed.apps.services.model.users.RoomUser
import nabed.apps.services.model.users.User


internal val RoomUser.toUser: User
    get() = User(
        this.id,
        this.username
    )

internal fun List<RoomUser>.toUsersListFromRoomUsers(): List<User> = this.flatMap {
    listOf(it.toUser)
}

internal val RoomPhoto.toPhoto: Photo
    get() = Photo(
        this.id,
        this.imageHeight,
        this.imageSize,
        this.imageWidth,
        this.largeImageURL,
        this.likes,
        this.pageURL,
        this.previewHeight,
        this.previewURL,
        this.previewWidth,
        this.user,
        this.views
    )

internal val Photo.toRoomPhoto: RoomPhoto
    get() = RoomPhoto(
        this.id,
        this.imageHeight,
        this.imageSize,
        this.imageWidth,
        this.largeImageURL,
        this.likes,
        this.pageURL,
        this.previewHeight,
        this.previewURL,
        this.previewWidth,
        this.user,
        this.views
    )

internal fun List<RoomPhoto>.toPhotosListFromRoomPhotos(): List<Photo> = this.flatMap {
    listOf(it.toPhoto)
}

internal fun List<Photo>.toPhotosRoomListFromPhotos(): List<RoomPhoto> = this.flatMap {
    listOf(it.toRoomPhoto)
}



