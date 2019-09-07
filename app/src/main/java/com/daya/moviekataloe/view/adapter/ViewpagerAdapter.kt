package com.daya.moviekataloe.view.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewpagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    val fragment = arrayListOf<Fragment>()

    override fun getItem(position: Int): Fragment = fragment[position]
    override fun getCount(): Int = fragment.size
}