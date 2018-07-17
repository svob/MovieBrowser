package cz.svobodaf.moviebrowser.data.api

import cz.svobodaf.moviebrowser.model.PopularResponse
import cz.svobodaf.moviebrowser.model.TopRatedResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    fun getPopularMovies(@Query("page") page: Int): Call<PopularResponse>

    @GET("movie/top_rated")
    fun getTopRated(@Query("page") page: Int): Call<TopRatedResponse>

    companion object {
        const val PAGE_SIZE = 20
        val api = RetrofitClient.client.create(MovieApi::class.java)
    }
}