package cz.svobodaf.moviebrowser.Navigation

import android.support.v7.widget.Toolbar
import cz.svobodaf.moviebrowser.BaseActivity
import cz.svobodaf.moviebrowser.R
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity() {

    override fun getContentLayoutResId() = R.layout.activity_main

    override fun getToolbar(): Toolbar? = toolbar
}
