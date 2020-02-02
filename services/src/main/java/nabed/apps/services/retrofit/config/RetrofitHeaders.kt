package nabed.apps.services.retrofit.config


import okhttp3.Headers

class RetrofitHeaders {


     var requestHeaders: Headers? = null

    init {
        requestHeaders = buildRetrofitRequestHeaders()
    }


    fun addHeader(key: String, value: String) {
        requestHeaders = requestHeaders!!.newBuilder()
            .removeAll(key)
            .add(key, value)
            .build()
    }

    fun removeHeader(key: String) {
        requestHeaders = requestHeaders!!.newBuilder()
            .removeAll(key)
            .build()
    }

    fun provideRetrofitHeaders(): Headers? {
        return buildRetrofitRequestHeaders()

    }


    fun buildRetrofitRequestHeaders(): Headers {
        val headersBuilder = Headers.Builder()
            .add("Content-Type", "application/json")

        return headersBuilder.build()
    }


}
