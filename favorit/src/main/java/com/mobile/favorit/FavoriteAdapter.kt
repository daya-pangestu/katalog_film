package com.mobile.favorit

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import java.lang.ref.WeakReference

class FavoriteAdapter(context: Context, fm: FragmentManager, list: List<Fragment>) :
    FragmentPagerAdapter(fm) {
    private var fragment = arrayListOf<Fragment>()
    private var weakContext: WeakReference<Context> = WeakReference(context)

    init {
        this.fragment.addAll(list)
    }

    override fun getItem(position: Int): Fragment = fragment[position]
    override fun getCount(): Int = fragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {

            0 -> weakContext.get()?.getString(R.string.movie) ?: "1"
            else -> weakContext.get()?.getString(R.string.tv) ?: "2"
        }
    }
}