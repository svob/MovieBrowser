package cz.svobodaf.moviebrowser.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import cz.svobodaf.moviebrowser.data.Repository.MovieRepository
import cz.svobodaf.moviebrowser.model.MovieListItem

class MovieListViewModel : ViewModel() {
    private val movieRepository = MovieRepository() // TODO: DI
    private val inputLiveData = MutableLiveData<Int>()
    lateinit var movieList: LiveData<List<MovieListItem>>

    fun init(type: String, page: Int = 1) {
        if (type == ARG_LIST_TYPE_POPULAR) {
            movieList = Transformations.switchMap(inputLiveData, movieRepository::getNewMovies)
            updateMovieListPopular(page)
        } else if (type == ARG_LIST_TYPE_TOP_RANK) {
            movieList = Transformations.switchMap(inputLiveData, movieRepository::getTopMovies)
            updateMovieListTop(page)
        }
    }

    private fun updateMovieListPopular(page: Int) {
        inputLiveData.value = page
    }

    private fun updateMovieListTop(page: Int) {
        inputLiveData.value = page
    }

    companion object {
        val ARG_LIST_TYPE_POPULAR = "LIST_TYPE_POPULAR"
        val ARG_LIST_TYPE_TOP_RANK = "LIST_TYPE_TOP_RANK"
    }
}