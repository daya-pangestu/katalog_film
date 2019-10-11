package com.mobile.favorit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_movie_fav.*

/**
 * A simple [Fragment] subclass.
 */
class movieFavFragment : Fragment() {
    private lateinit var model: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        model.getListMovie().observe(this, Observer {
            initRecyclerViewMovie(it)
        })
    }


    private fun initRecyclerViewMovie(
        movieModel: List<MovieFavModel>?
    ) {
        val movieAdapter = MediaAdapter(MediaAdapter.TYPE_FAV_MOVIE)
        movieAdapter.listMovie = movieModel
        fmovieFavorite.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
    }
}
