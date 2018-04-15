package cz.svobodaf.moviebrowser

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (getContentLayoutResId() != 0) {
            setContentView(getContentLayoutResId())
        }

        setSupportActionBar(getToolbar())
        supportActionBar?.apply {
            setDisplayShowTitleEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    protected abstract fun getContentLayoutResId(): Int

    protected open fun getToolbar(): Toolbar? = null
}