package cz.svobodaf.moviebrowser

import android.util.SparseArray

fun <T> SparseArray<T>.toList(): List<T> {
    val list = ArrayList<T>()
    for (i in 0 until size()) {
        val key = keyAt(i)
        list.add(this[key])
    }
    return list
}