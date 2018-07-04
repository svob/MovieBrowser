package cz.svobodaf.moviebrowser.fragment


import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.activity.DetailActivity
import cz.svobodaf.moviebrowser.list.MovieListAdapter
import cz.svobodaf.moviebrowser.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : BaseFragment() {
    private lateinit var  viewModel: MovieListViewModel
    private lateinit var viewAdapter: MovieListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelProvider.AndroidViewModelFactory(Application())).get(MovieListViewModel::class.java)
        attachObservers()

        val type = MovieListFragmentArgs.fromBundle(arguments).fragmenT_TYPE
        val actionId = when (type) {
            MovieListViewModel.ARG_LIST_TYPE_POPULAR -> {
                setTitle(R.string.nav_news) // TODO: setup toolbar with navigation component
                R.id.action_nav_news_to_detailActivity
            }
            MovieListViewModel.ARG_LIST_TYPE_TOP_RANK -> {
                setTitle(R.string.nav_top_ranked)
                R.id.action_nav_top_rank_to_detailActivity
            }
            else -> -1
        }

        if (savedInstanceState == null) {
            viewModel.init(type)
        }

        context?.let { context ->
            viewManager = GridLayoutManager(context, 3)
            viewAdapter = MovieListAdapter(
                    viewModel.movieList.value?.toMutableList() ?: ArrayList(),
                    context,
                    { movie, holder ->
                        // TODO: Use this where there is support for shared element transitons in navigation components https://issuetracker.google.com/issues/79665225
//                        val bundle = Bundle().apply {
//                            putParcelable(DetailActivity.EXTRA_MOVIE, movie)
//                        }
//                        Navigation.findNavController(activity as Activity, R.id.nav_host).navigate(actionId, bundle)
                        DetailActivity.start(context, movie, activity, holder.image)
                    },
                    { page ->
                        viewModel.init(type, page)
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

    private fun attachObservers() {
        viewModel.movieList.observe(this, Observer {
            it?.let {
                viewAdapter.addData(it)
            }
        })
    }
}
