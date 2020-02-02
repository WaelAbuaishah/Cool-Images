package nabed.apps.services.model.photos

import androidx.room.*

@Dao
interface PhotoDao {

    @Query("SELECT * FROM photos")
    suspend fun getPhotos(): List<RoomPhoto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdatePhoto(photo: RoomPhoto):Long

    @Query("DELETE FROM photos")
    fun deleteAllPhotos()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(photos: List<RoomPhoto>)

}