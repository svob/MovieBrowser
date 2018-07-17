package cz.svobodaf.moviebrowser.data.Repository

import android.arch.lifecycle.LiveData
import cz.svobodaf.moviebrowser.model.MovieListItem

interface IMovieRepository {

    fun getNewMovies(page: Int): LiveData<List<MovieListItem>>

    fun getTopMovies(page: Int): LiveData<List<MovieListItem>>
}
