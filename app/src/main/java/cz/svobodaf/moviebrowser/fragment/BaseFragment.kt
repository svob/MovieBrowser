package cz.svobodaf.moviebrowser.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (getContentLayoutResId() != 0) inflater.inflate(getContentLayoutResId(), container, false) else null
    }

    protected abstract fun getContentLayoutResId(): Int
}
