package cz.svobodaf.moviebrowser.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.viewmodel.MovieListViewModel

class MovieListFragment : Fragment() {
    private lateinit var  viewModel: MovieListViewModel

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        attachObservers()
        viewModel.init(MovieListViewModel.Companion.ListType.POPULAR) // TODO
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_list, container, false)
    }

    private fun attachObservers() {
        viewModel.movieList.observe(this, Observer {
            it?.let {
                // TODO: update adapter
            }
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                MovieListFragment().apply {
                    arguments = Bundle().apply {
                    }
                }
    }
}
