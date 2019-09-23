package com.daya.moviekataloe.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.daya.moviekataloe.view.MovieFragment
import com.daya.moviekataloe.view.TvFragment
import com.daya.moviekataloe.view.favorite.FavoriteContainerFragment

class ViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    private val fragment = arrayListOf(MovieFragment(), TvFragment(), FavoriteContainerFragment())

    override fun getItem(position: Int): Fragment = fragment[position]
    override fun getCount(): Int = fragment.size
}