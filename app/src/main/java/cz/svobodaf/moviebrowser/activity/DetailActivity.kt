package cz.svobodaf.moviebrowser.activity

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.Toolbar
import android.util.SparseArray
import android.view.Menu
import android.view.MenuItem
import cz.svobodaf.moviebrowser.Preferences
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.model.MovieListItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : BaseActivity() {

    private var favMenuItem: MenuItem? = null
    private lateinit var favMovies: SparseArray<MovieListItem>
    private val movie = MovieListItem()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favMovies = Preferences(this).favoriteMovies

        movie.id = intent.getIntExtra(EXTRA_MOVIE_ID, 0)
        movie.title = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        movie.releaseDate = intent.getStringExtra(EXTRA_MOVIE_DATE)
        movie.voteAverage = intent.getDoubleExtra(EXTRA_MOVIE_RATING, 0.0)
        movie.overview = intent.getStringExtra(EXTRA_MOVIE_DESC)

        movie_title.text = movie.title
        movie_date.text = movie.releaseDate
        movie_rating.text = movie.voteAverage.toString()
        movie_description.text = movie.overview
    }

    override fun getContentLayoutResId() = R.layout.activity_detail

    override fun getToolbar(): Toolbar? = toolbar

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater?.inflate(R.menu.detail_menu, menu)
        favMenuItem = menu?.findItem(R.id.favorites)

        if (favMovies[movie.id] != null) {
            favMenuItem?.setIcon(R.drawable.ic_favorite_filled)
        } else {
            favMenuItem?.setIcon(R.drawable.ic_favorite)
        }

        favMenuItem?.icon?.colorFilter = PorterDuffColorFilter(ContextCompat.getColor(this, R.color.colorAccent), PorterDuff.Mode.SRC_IN)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            R.id.favorites -> {
                addOrRemoveFavorite()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addOrRemoveFavorite() {
        if (favMovies[movie.id] != null) { // was favorite
            favMovies.remove(movie.id)
        } else {
            favMovies.append(movie.id, movie)
        }

        Preferences(this).favoriteMovies = favMovies
        invalidateOptionsMenu()
    }

    companion object {
        const val EXTRA_MOVIE_ID = "MOVIE_ID"
        const val EXTRA_MOVIE_TITLE = "MOVIE_TITLE"
        const val EXTRA_MOVIE_DATE = "MOVIE_DATE"
        const val EXTRA_MOVIE_RATING = "MOVIE_RATING"
        const val EXTRA_MOVIE_DESC = "MOVIE_DESC"
        const val EXTRA_MOVIE_IMAGE_PATH = "MOVIE_IMAGE_PATH"


        fun start(parent: Context, movie: MovieListItem) {
            val intent = Intent(parent, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_ID, movie.id)
            intent.putExtra(EXTRA_MOVIE_TITLE, movie.title)
            intent.putExtra(EXTRA_MOVIE_DATE, movie.releaseDate)
            intent.putExtra(EXTRA_MOVIE_RATING, movie.voteAverage)
            intent.putExtra(EXTRA_MOVIE_DESC, movie.overview)
            intent.putExtra(EXTRA_MOVIE_IMAGE_PATH, movie.backdropPath)
            parent.startActivity(intent)
        }
    }
}
