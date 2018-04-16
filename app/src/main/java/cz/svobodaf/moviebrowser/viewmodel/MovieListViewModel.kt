package cz.svobodaf.moviebrowser.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import cz.svobodaf.moviebrowser.api.MovieApi
import cz.svobodaf.moviebrowser.model.MovieListItem
import cz.svobodaf.moviebrowser.model.PopularResponse
import cz.svobodaf.moviebrowser.model.TopRatedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel : ViewModel() {
    var movieList: MutableLiveData<List<MovieListItem>> = MutableLiveData()
    var pf = 1

    init {
        pf = 2
    }

    fun init(type: String) {
        if (type == ARG_LIST_TYPE_POPULAR) {
            updateMovieListPopular()
        } else if (type == ARG_LIST_TYPE_TOP_RANK) {
            updateMovieListTop()
        }
    }

    private fun updateMovieListPopular() {
        val call = MovieApi.api.getPopularMovies(1)
        call.enqueue(object: Callback<PopularResponse> {
            override fun onFailure(call: Call<PopularResponse>?, t: Throwable?) {
                Log.e("MovieListViewModel", "Failed to make api call", t)
            }

            override fun onResponse(call: Call<PopularResponse>, response: Response<PopularResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    movieList.postValue(response.body()?.results)
                }
            }
        })
    }

    private fun updateMovieListTop() {
        val call = MovieApi.api.getTopRated(1)
        call.enqueue(object: Callback<TopRatedResponse> {
            override fun onFailure(call: Call<TopRatedResponse>?, t: Throwable?) {
                Log.e("MovieListViewModel", "Failed to make api call", t)
            }

            override fun onResponse(call: Call<TopRatedResponse>, response: Response<TopRatedResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    movieList.postValue(response.body()?.results)
                }
            }
        })
    }

    companion object {
        val ARG_LIST_TYPE_POPULAR = "LIST_TYPE_POPULAR"
        val ARG_LIST_TYPE_TOP_RANK = "LIST_TYPE_TOP_RANK"
    }
}