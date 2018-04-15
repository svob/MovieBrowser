package cz.svobodaf.moviebrowser.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import retrofit2.Callback
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.api.MovieApi
import cz.svobodaf.moviebrowser.fragment.FavoriteMoviesFragment
import cz.svobodaf.moviebrowser.fragment.MovieListFragment
import cz.svobodaf.moviebrowser.model.PopularResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import retrofit2.Call
import retrofit2.Response

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        changeFragment(MovieListFragment::class.java)

        nav_bottom.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_news ->  {
                    changeFragment(MovieListFragment::class.java)
                    true
                }
                R.id.nav_top_rank -> {
                    changeFragment(MovieListFragment::class.java)
                    true
                }
                R.id.favorites -> {
                    changeFragment(FavoriteMoviesFragment::class.java)
                    true
                }
                else -> false
            }
        }

        getMovies()
    }

    override fun getContentLayoutResId() = R.layout.activity_main

    override fun getToolbar(): Toolbar? = toolbar

    private fun changeFragment(fragmentClass: Class<*>) {
        val fragment = fragmentClass.newInstance() as Fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .commit()
    }

    fun getMovies() {
        val call = MovieApi.api.getPopularMovies(1)
        call.enqueue(object: Callback<PopularResponse> {
            override fun onFailure(call: Call<PopularResponse>?, t: Throwable?) {
                // TODO: log
            }

            override fun onResponse(call: Call<PopularResponse>?, response: Response<PopularResponse>?) {
                // TODO: successHandler
            }
        })
    }
}
