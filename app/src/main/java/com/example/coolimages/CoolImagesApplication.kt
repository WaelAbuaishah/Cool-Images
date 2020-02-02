package com.example.coolimages

import android.app.Application
import com.example.coolimages.di.ActivityModule
import com.example.coolimages.di.UserViewModelModule
import nabed.apps.services.repository.di.RepositoryModule
import nabed.apps.services.retrofit.di.NetworkModule
import nabed.apps.services.utils.cachingUtils.SharedPreferencesUtils
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

import org.koin.core.logger.Level


class CoolImagesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        SharedPreferencesUtils.initializeSharedPrefsService(this)
        startKoin {
            androidContext(this@CoolImagesApplication)

            printLogger(level = Level.DEBUG)
            modules(
                listOf(
                    NetworkModule,
                    RepositoryModule,
                    UserViewModelModule,
                    ActivityModule
                )
            )
        }
    }
}
