package com.mobile.favorit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_tv_fav.*

class tvFavFragment : Fragment() {
    private lateinit var model: SharedViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tv_fav, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = activity?.run {
            ViewModelProviders.of(this).get(SharedViewModel::class.java)
        } ?: throw Exception("Invalid Activity")


        model.getLIstTv().observe(this, Observer {
            initRecyclerViewTv(it)
        })
    }

    fun initRecyclerViewTv(
        tvModel: List<TvFavModel>
    ) {
        val tvAdapter = MediaAdapter(MediaAdapter.TYPE_FAV_TV)
        tvAdapter.listTv = tvModel
        fTvFavorite.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }
}
