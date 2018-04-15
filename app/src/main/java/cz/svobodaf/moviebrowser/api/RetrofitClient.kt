package cz.svobodaf.moviebrowser.api

import cz.svobodaf.moviebrowser.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    val client = create()


    private fun create(): Retrofit {
        val httpClient = OkHttpClient().newBuilder().addInterceptor { chain ->
            val originalRequest = chain.request()
            val originalUrl = originalRequest.url()

            val url = originalUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.API_KEY)
                    .build()

            val request = originalRequest.newBuilder()
                    .url(url)
                    .build()

            chain.proceed(request)
        }.build()

        return Retrofit.Builder()
                .baseUrl(BuildConfig.API_BASE_URL)
                .client(httpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
    }
}