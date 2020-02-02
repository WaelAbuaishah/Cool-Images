package nabed.apps.services.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import nabed.apps.services.model.photos.PhotoDao
import nabed.apps.services.model.photos.RoomPhoto
import nabed.apps.services.model.users.RoomUser
import nabed.apps.services.model.users.UserDao

private const val DATABASE = "cool_images_database"

@Database(
    entities = [RoomUser::class, RoomPhoto::class],
    version = 4,
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun roomNoteDao(): UserDao
    abstract fun roomPhotosDao(): PhotoDao


    companion object {

        @Volatile
        private var instance: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            return instance ?: synchronized(this) {
                instance
                    ?: buildDatabase(
                        context
                    ).also { instance = it }
            }
        }

        fun buildDatabase(context: Context): AppRoomDatabase {
            return Room.databaseBuilder(context, AppRoomDatabase::class.java,
                DATABASE
            )
                .build()
        }
    }
}