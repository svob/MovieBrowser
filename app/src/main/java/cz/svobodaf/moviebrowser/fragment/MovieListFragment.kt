package cz.svobodaf.moviebrowser.fragment


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.model.MovieListItem
import cz.svobodaf.moviebrowser.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieListFragment : Fragment() {
    private lateinit var  viewModel: MovieListViewModel
    private lateinit var viewAdapter: RecyclerViewAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MovieListViewModel::class.java)
        attachObservers()
        viewModel.init(MovieListViewModel.Companion.ListType.POPULAR) // TODO

        viewManager = GridLayoutManager(context, 3) // TODO: column count land.
        viewAdapter = RecyclerViewAdapter(viewModel.movieList.value ?: ArrayList())

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

    class RecyclerViewAdapter(var dataSet: List<MovieListItem>) : RecyclerView.Adapter<RecyclerViewAdapter.MovieViewHolder>() {

        class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val image: ImageView = itemView.findViewById(R.id.movie_image)
            val title: TextView = itemView.findViewById(R.id.movie_title)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_grid_item, parent, false)
            return MovieViewHolder(view)
        }

        override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
//            holder.image.set
            holder.title.text = dataSet[position].title
        }

        override fun getItemCount() = dataSet.size

        fun setData(data: List<MovieListItem>) {
            dataSet = data
            notifyDataSetChanged()
        }
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
