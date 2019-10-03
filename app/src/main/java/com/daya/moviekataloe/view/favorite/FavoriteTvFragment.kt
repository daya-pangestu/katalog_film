package com.daya.moviekataloe.view.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.daya.moviekataloe.R
import com.daya.moviekataloe.repo.room.TvFavTable
import com.daya.moviekataloe.view.adapter.MediaAdapter
import com.daya.moviekataloe.viewmodel.FavoriteViewModel
import kotlinx.android.synthetic.main.fragment_favorite_tv.*

class FavoriteTvFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val movieVModel by lazy {
            ViewModelProviders.of(activity!!).get(FavoriteViewModel::class.java)
        }

        movieVModel.getAllFavoriteTv().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.isNotEmpty()) {
                    initRecyclerView(it)
                    fFavoriteTvEmpty.visibility = View.GONE
                } else {
                    initRecyclerView(it)
                    fFavoriteTvEmpty.visibility = View.VISIBLE
                }
            } else {
                fFavoriteTvEmpty.visibility = View.VISIBLE
            }
        })
    }

    private fun initRecyclerView(movieModel: List<TvFavTable>) {
        val movieAdapter = MediaAdapter(MediaAdapter.TYPE_FAV_TV)
        movieAdapter.tvFavModel = movieModel
        ffavoritTv.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }

}
