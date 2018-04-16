package cz.svobodaf.moviebrowser.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.model.MovieListItem
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        movie_title.text = intent.getStringExtra(EXTRA_MOVIE_TITLE)
        movie_date.text = intent.getStringExtra(EXTRA_MOVIE_DATE)
        movie_rating.text = intent.getDoubleExtra(EXTRA_MOVIE_RATING, 0.0).toString()
        movie_description.text = intent.getStringExtra(EXTRA_MOVIE_DESC)
    }

    override fun getContentLayoutResId() = R.layout.activity_detail

    override fun getToolbar(): Toolbar? = toolbar

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        const val EXTRA_MOVIE_TITLE = "MOVIE_TITLE"
        const val EXTRA_MOVIE_DATE = "MOVIE_DATE"
        const val EXTRA_MOVIE_RATING = "MOVIE_RATING"
        const val EXTRA_MOVIE_DESC = "MOVIE_DESC"
        const val EXTRA_MOVIE_IMAGE_PATH = "MOVIE_IMAGE_PATH"


        fun start(parent: Context, movie: MovieListItem) {
            val intent = Intent(parent, DetailActivity::class.java)
            intent.putExtra(EXTRA_MOVIE_TITLE, movie.title)
            intent.putExtra(EXTRA_MOVIE_DATE, movie.releaseDate)
            intent.putExtra(EXTRA_MOVIE_RATING, movie.voteAverage)
            intent.putExtra(EXTRA_MOVIE_DESC, movie.overview)
            intent.putExtra(EXTRA_MOVIE_IMAGE_PATH, movie.backdropPath)
            parent.startActivity(intent)
        }
    }
}
