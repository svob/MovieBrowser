package cz.svobodaf.moviebrowser.data.Repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.util.Log
import cz.svobodaf.moviebrowser.data.api.MovieApi
import cz.svobodaf.moviebrowser.model.MovieListItem
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

class MovieRepository : IMovieRepository {

    private val movieApi = MovieApi.api // TODO: DI/service locator
    private val liveData = MutableLiveData<List<MovieListItem>>()

    override fun getNewMovies(page: Int): LiveData<List<MovieListItem>> {
        launch {
            val response = getNewMoviesFromRepo(page).await()
            liveData.postValue(response)
        }
        return liveData
    }

    private fun getNewMoviesFromRepo(page: Int): Deferred<List<MovieListItem>> {
        return async {
            try {
                val response = movieApi.getPopularMovies(page).execute()
                if (response.isSuccessful) {
                    response.body()?.results ?: ArrayList()
                } else {
                    ArrayList()
                }
            } catch (e: Exception) {
                Log.d(TAG, "Failed to make api call", e)
                ArrayList<MovieListItem>()
            }
        }
    }

    override fun getTopMovies(page: Int): LiveData<List<MovieListItem>> {
        launch {
            val response = getTopMoviesFromRepo(page).await()
            liveData.postValue(response)
        }
        return liveData
    }

    private fun getTopMoviesFromRepo(page: Int): Deferred<List<MovieListItem>> {
        return async {
            try {
                val response = movieApi.getTopRated(page).execute()
                if (response.isSuccessful) {
                    response.body()?.results ?: ArrayList()
                } else {
                    ArrayList()
                }
            } catch (e: Exception) {
                Log.d(TAG, "Failed to make api call", e)
                ArrayList<MovieListItem>()
            }
        }
    }

    companion object {
        private const val TAG = "MovieRepository"
    }
}