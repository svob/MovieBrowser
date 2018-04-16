package cz.svobodaf.moviebrowser.list

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.request.RequestOptions
import cz.svobodaf.moviebrowser.BuildConfig
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.model.MovieListItem

class MovieListAdapter(
        private var dataSet: List<MovieListItem>,
        private val context: Context,
        private val clickListener: (movie: MovieListItem, holder: MovieViewHolder) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.movie_image)
        val title: TextView = itemView.findViewById(R.id.movie_title)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_grid_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.itemView.setOnClickListener { clickListener(movie, holder) }

        val options = RequestOptions()
                .centerCrop()
                .priority(Priority.HIGH)

        Glide
                .with(context)
                .asBitmap()
                .load(BuildConfig.API_BASE_IMAGE_URL + "w300/" + movie.backdropPath)
                .apply(options)
                .into(holder.image)

        holder.title.text = movie.title
    }

    override fun getItemCount() = dataSet.size

    fun setData(data: List<MovieListItem>) {
        dataSet = data
        notifyDataSetChanged()
    }
}