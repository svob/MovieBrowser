package cz.svobodaf.moviebrowser.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import cz.svobodaf.moviebrowser.api.MovieApi
import cz.svobodaf.moviebrowser.model.PopularItem
import cz.svobodaf.moviebrowser.model.PopularResponse
import cz.svobodaf.moviebrowser.model.TopRatedResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieListViewModel : ViewModel() {
    var movieList: MutableLiveData<List<PopularItem>> = MutableLiveData()

    fun init(type: ListType) {
        if (type == ListType.POPULAR) {
            updateMovieListPopular()
        } else if (type == ListType.TOP_RANK) {
            updateMovieListTop()
        }
    }

    private fun updateMovieListPopular() {
        val call = MovieApi.api.getPopularMovies(1)
        call.enqueue(object: Callback<PopularResponse> {
            override fun onFailure(call: Call<PopularResponse>?, t: Throwable?) {
                // TODO: log
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
                // TODO: log
            }

            override fun onResponse(call: Call<TopRatedResponse>, response: Response<TopRatedResponse>) {
                if (response.isSuccessful && response.body() != null) {
                    movieList.postValue(response.body()?.results)
                }
            }
        })
    }

    companion object {
        enum class ListType {
            POPULAR,
            TOP_RANK
        }
    }
}