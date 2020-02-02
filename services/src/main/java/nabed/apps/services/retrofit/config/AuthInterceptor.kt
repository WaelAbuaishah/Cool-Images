package nabed.apps.services.retrofit.config


import okhttp3.Interceptor
import okhttp3.Response


class AuthInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
        val headers = requestHeaders
        requestBuilder.headers(headers.requestHeaders!!)
        val request = requestBuilder.build()
        val initialResponse = chain.proceed(request)


        return when {
            initialResponse.code in 200..299 -> {
                initialResponse
            }

            else -> {
                return initialResponse
            }
        }
    }

    companion object {
        var requestHeaders = RetrofitHeaders()

        fun resetHeaders(){
            requestHeaders.removeHeader("Content-Type")
            requestHeaders.provideRetrofitHeaders()
        }
        fun addHeader(key: String, value: String) {
            if (requestHeaders == null) {
                return
            }
            requestHeaders!!.addHeader(key, value)
        }

        fun replaceHeader(key: String, value: String) {
            if (requestHeaders == null) {
                return
            }

            requestHeaders!!.addHeader(key, value)
        }

        fun removeHeader(key: String) {
            if (requestHeaders == null) {
                return
            }
            requestHeaders!!.removeHeader(key)
        }
    }
}