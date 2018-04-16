package cz.svobodaf.moviebrowser.model

import com.squareup.moshi.Json

data class TopRatedResponse(@field:Json(name = "page")
                            val page: Int = 0,
                            @field:Json(name = "total_pages")
                            val totalPages: Int = 0,
                            @field:Json(name = "results")
                            val results: List<MovieListItem>?,
                            @field:Json(name = "total_results")
                            val totalResults: Int = 0)