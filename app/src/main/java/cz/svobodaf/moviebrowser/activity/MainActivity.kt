package cz.svobodaf.moviebrowser.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.Toolbar
import cz.svobodaf.moviebrowser.R
import cz.svobodaf.moviebrowser.fragment.FavoriteMoviesFragment
import cz.svobodaf.moviebrowser.fragment.MovieListFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

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
    }

    override fun getContentLayoutResId() = R.layout.activity_main

    override fun getToolbar(): Toolbar? = toolbar

    private fun changeFragment(fragmentClass: Class<*>) {
        val fragment = fragmentClass.newInstance() as Fragment
        supportFragmentManager.beginTransaction()
                .add(R.id.content, fragment)
                .commit()
    }
}
