package com.daya.moviekataloe.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.daya.moviekataloe.view.favorite.FavoriteMovieFragment
import com.daya.moviekataloe.view.favorite.FavoriteTvFragment

class FavoriteAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val fragment = arrayListOf(FavoriteMovieFragment(), FavoriteTvFragment())

    override fun getItem(position: Int): Fragment = fragment[position]
    override fun getCount(): Int = fragment.size

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "movie favorite"
            else -> "tv favorite"
        }
    }
}