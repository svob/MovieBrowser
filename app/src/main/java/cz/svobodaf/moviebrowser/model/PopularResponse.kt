package cz.svobodaf.moviebrowser.model

import com.squareup.moshi.Json

data class PopularResponse(@Json(name = "page")
                           val page: Int = 0,
                           @Json(name = "total_pages")
                           val totalPages: Int = 0,
                           @Json(name = "results")
                           val results: List<MovieListItem>?,
                           @Json(name = "total_results")
                           val totalResults: Int = 0)