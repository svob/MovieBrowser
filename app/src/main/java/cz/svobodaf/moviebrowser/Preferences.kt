package cz.svobodaf.moviebrowser

import android.content.Context
import android.preference.PreferenceManager
import android.util.SparseArray
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import cz.svobodaf.moviebrowser.model.MovieListItem
import cz.svobodaf.moviebrowser.model.adapter.SparseArrayJsonAdapter

class Preferences(context: Context) {

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    var favoriteMovies: SparseArray<MovieListItem>
        get() {
            val moshi = Moshi.Builder().add(SparseArrayJsonAdapter.Factory).build()
            val adapter = moshi.adapter<SparseArray<MovieListItem>>(
                    Types.newParameterizedType(SparseArray::class.java, MovieListItem::class.java)
            )
            return adapter.fromJson(prefs.getString(MOVIE_LIST, "[]")) ?: SparseArray()
        }
    set(value) {
        val moshi = Moshi.Builder().add(SparseArrayJsonAdapter.Factory).build()
        val adapter = moshi.adapter<SparseArray<MovieListItem>>(
                Types.newParameterizedType(SparseArray::class.java, MovieListItem::class.java)
        )
        prefs.edit().putString(MOVIE_LIST, adapter.toJson(value)).apply()
    }

    companion object {
        const val MOVIE_LIST = "MOVIE_LIST"
    }
}