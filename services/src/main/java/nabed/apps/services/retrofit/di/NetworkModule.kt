package nabed.apps.services.retrofit.di


import nabed.apps.services.retrofit.config.*
import nabed.apps.services.retrofit.utils.ResponseHandler
import org.koin.dsl.module


val NetworkModule = module {


    factory { AuthInterceptor() }
    factory {
        provideOkHttpClient(
            get(),
            get(),
            provideSSLContext(provideX509TrustManager()),
            provideX509TrustManager()
        )
    }
    factory { provideLoggingInterceptor() }

    single { provideRetrofit(get()) }

    factory { provideAppAPI(get()) }
    factory { ResponseHandler() }


}