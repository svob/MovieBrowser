package cz.svobodaf.moviebrowser.model

import android.os.Parcelable
import com.squareup.moshi.Json
import cz.svobodaf.moviebrowser.model.adapter.ISparseArrayItem
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieListItem(@field:Json(name = "overview")
                 var overview: String = "",
                         @field:Json(name = "original_language")
                 var originalLanguage: String = "",
                         @field:Json(name = "original_title")
                 var originalTitle: String = "",
                         @field:Json(name = "video")
                 var video: Boolean = false,
                         @field:Json(name = "title")
                 var title: String = "",
                         @field:Json(name = "genre_ids")
                 var genreIds: List<Int>? = ArrayList(),
                         @field:Json(name = "poster_path")
                 var posterPath: String = "",
                         @field:Json(name = "backdrop_path")
                 var backdropPath: String = "",
                         @field:Json(name = "release_date")
                 var releaseDate: String = "",
                         @field:Json(name = "vote_average")
                 var voteAverage: Double = 0.0,
                         @field:Json(name = "popularity")
                 var popularity: Double = 0.0,
                         @field:Json(name = "id")
                 override var id: Int = 0,
                         @field:Json(name = "adult")
                 var adult: Boolean = false,
                         @field:Json(name = "vote_count")
                 var voteCount: Int = 0
) : ISparseArrayItem, Parcelable