package cz.svobodaf.moviebrowser.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import cz.svobodaf.moviebrowser.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navController = (nav_host as NavHostFragment).navController
        nav_bottom.setupWithNavController(navController)
        setupActionBarWithNavController(navController)
    }

    override fun getContentLayoutResId() = R.layout.activity_main

    override fun getToolbar(): Toolbar? = toolbar

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }
}
