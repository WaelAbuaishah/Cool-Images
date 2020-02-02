package nabed.apps.services.retrofit.config



import nabed.apps.services.BuildConfig
import nabed.apps.services.utils.Constants.PROTOCOL_SSL
import nabed.apps.services.utils.Constants.TIMEOUT

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.security.cert.CertificateException
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager


fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun provideOkHttpClient(
    authInterceptor: AuthInterceptor,
    loggingInterceptor: HttpLoggingInterceptor,
    sslContext: SSLContext,
    x509TrustManager: X509TrustManager
): OkHttpClient {
    return OkHttpClient()
        .newBuilder()
        .sslSocketFactory(sslContext.socketFactory, x509TrustManager)
        .addInterceptor(authInterceptor)
        .addInterceptor(loggingInterceptor)
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .build()
}


fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val logger = HttpLoggingInterceptor()
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun provideSSLContext(x509TrustManager: X509TrustManager): SSLContext {

    try {

        val trustManagers = arrayOf<TrustManager>(x509TrustManager)

        val sslContext = SSLContext.getInstance(PROTOCOL_SSL)
        sslContext.init(null, trustManagers, java.security.SecureRandom())
        return sslContext
    } catch (e: Exception) {
        throw RuntimeException("Could not provide SSL context!", e)
    }

}

fun provideX509TrustManager(): X509TrustManager {

    return object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate?> {

            return arrayOfNulls(0)
        }

        @Throws(CertificateException::class)
        override fun checkServerTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {

        }

        @Throws(CertificateException::class)
        override fun checkClientTrusted(
            chain: Array<X509Certificate>,
            authType: String
        ) {

        }
    }

}

fun provideAppAPI(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)


