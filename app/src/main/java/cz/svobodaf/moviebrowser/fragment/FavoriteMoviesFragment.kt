package cz.svobodaf.moviebrowser.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cz.svobodaf.moviebrowser.Preferences

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.activity.DetailActivity
import cz.svobodaf.moviebrowser.list.MovieListAdapter
import cz.svobodaf.moviebrowser.model.MovieListItem
import kotlinx.android.synthetic.main.fragment_movie_list.*

class FavoriteMoviesFragment : Fragment() {
    private lateinit var viewAdapter: MovieListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        context?.let { context ->
            viewManager = GridLayoutManager(context, 3)

            val movies = Preferences(context).favoriteMovies
            val movieList = ArrayList<MovieListItem>()
            for (i in 0 until movies.size()) {
                val key = movies.keyAt(i)
                movieList.add(movies[key])
            }

            viewAdapter = MovieListAdapter(
                    movieList,
                    context,
                    { movie, holder ->
                        DetailActivity.start(context, movie, activity, holder.image)
                    }
            )
        }

        recycler_view.apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    companion object {
        fun newInstance() = FavoriteMoviesFragment()
    }
}
