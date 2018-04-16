package cz.svobodaf.moviebrowser.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.fragment.FavoriteMoviesFragment
import cz.svobodaf.moviebrowser.fragment.MovieListFragment
import cz.svobodaf.moviebrowser.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            val fragment = MovieListFragment.newInstance(MovieListViewModel.Companion.ARG_LIST_TYPE_POPULAR)
            changeFragment(fragment)
        }

        nav_bottom.setOnNavigationItemSelectedListener {
            val fragment = when (it.itemId) {
                R.id.nav_news -> MovieListFragment.newInstance(MovieListViewModel.Companion.ARG_LIST_TYPE_POPULAR)
                R.id.nav_top_rank -> MovieListFragment.newInstance(MovieListViewModel.Companion.ARG_LIST_TYPE_TOP_RANK)
                R.id.favorites -> FavoriteMoviesFragment.newInstance()
                else -> null
            }
            if (fragment != null) {
                changeFragment(fragment)
                true
            } else {
                false
            }
        }
    }

    override fun getContentLayoutResId() = R.layout.activity_main

    override fun getToolbar(): Toolbar? = toolbar

    private fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit()
    }
}
