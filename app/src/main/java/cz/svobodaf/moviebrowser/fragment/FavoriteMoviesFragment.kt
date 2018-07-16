package cz.svobodaf.moviebrowser.fragment

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import cz.svobodaf.moviebrowser.Preferences

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.activity.DetailActivity
import cz.svobodaf.moviebrowser.list.MovieListAdapter
import cz.svobodaf.moviebrowser.toList
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FavoriteMoviesFragment : BaseFragment() {
    private lateinit var viewAdapter: MovieListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let { context ->
            viewManager = GridLayoutManager(context, 3)

            val movies = Preferences(context).favoriteMovies.toList()

            viewAdapter = MovieListAdapter(
                    movies.toMutableList(),
                    context,
                    { movie, holder ->
                        DetailActivity.startForResult(this, movie, activity, holder.image)
                    }
            )
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun getContentLayoutResId() = R.layout.fragment_movie_list

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        context?.let {
            viewAdapter.setData(Preferences(it).favoriteMovies.toList())
        }
    }

    companion object {
        fun newInstance() = FavoriteMoviesFragment()
    }
}
