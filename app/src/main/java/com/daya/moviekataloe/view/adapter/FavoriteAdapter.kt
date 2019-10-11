package com.daya.moviekataloe.view.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.daya.moviekataloe.R
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

            0 -> weakContext.get()?.getString(R.string.title_tab) ?: "1"
            else -> weakContext.get()?.getString(R.string.tv_favorite) ?: "2"
        }
    }
}