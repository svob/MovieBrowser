package cz.svobodaf.moviebrowser.fragment


import android.app.Application
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.list.MovieListAdapter
import cz.svobodaf.moviebrowser.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {
    private lateinit var  viewModel: MovieListViewModel
    private lateinit var viewAdapter: MovieListAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, ViewModelProvider.AndroidViewModelFactory(Application())).get(MovieListViewModel::class.java)
        attachObservers()

        val type = arguments?.getString(ARG_FRAGMENT_TYPE, MovieListViewModel.ARG_LIST_TYPE_POPULAR)
        if (savedInstanceState == null) {
            viewModel.init(type!!)
        }

        viewManager = GridLayoutManager(context, 3)
        viewAdapter = MovieListAdapter(viewModel.movieList.value ?: ArrayList(), context!!)

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

    private fun attachObservers() {
        viewModel.movieList.observe(this, Observer {
            it?.let {
                viewAdapter.setData(it)
            }
        })
    }

    companion object {
        val ARG_FRAGMENT_TYPE = "FRAGMENT_TYPE"

        fun newInstance(type: String) =
                MovieListFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_FRAGMENT_TYPE, type)
                    }
                }
    }
}
