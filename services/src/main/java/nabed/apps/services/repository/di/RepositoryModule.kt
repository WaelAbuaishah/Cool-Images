package nabed.apps.services.repository.di

import androidx.room.Room
import nabed.apps.services.model.AppRoomDatabase
import nabed.apps.services.repository.photos.PhotosRepository
import nabed.apps.services.repository.user.UserRepository
import org.koin.dsl.module


val RepositoryModule = module {
    single {
        Room.databaseBuilder(get(), AppRoomDatabase::class.java, "cool_images_database")
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<AppRoomDatabase>().roomPhotosDao() }
    single { get<AppRoomDatabase>().roomNoteDao() }
    single { UserRepository(get(), get()) }
    single { PhotosRepository(get(), get(), get()) }

}