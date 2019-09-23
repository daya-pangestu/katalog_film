package com.daya.moviekataloe.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.adapter.FavoriteAdapter
import kotlinx.android.synthetic.main.fragment_favorite_conteiner.*

class FavoriteContainerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_conteiner, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fragmentAdapter = FavoriteAdapter(
            view.context,
            childFragmentManager,
            arrayListOf(FavoriteMovieFragment(), FavoriteTvFragment())
        )
        fFavoriteViewPager.adapter = fragmentAdapter
        toolbar.setViewPager(fFavoriteViewPager)

    }
}
