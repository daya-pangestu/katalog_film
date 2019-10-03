package com.mobile.favorit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_container.*

class ContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter = FavoriteAdapter(
            view.context,
            childFragmentManager,
            arrayListOf(movieFavFragment(), tvFavFragment())
        )
        viewPager.adapter = fragmentAdapter
        toolbar.setViewPager(viewPager)
    }
}
