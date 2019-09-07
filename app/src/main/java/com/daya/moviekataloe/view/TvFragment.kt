package com.daya.moviekataloe.view


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.adapter.MediaAdapter
import com.daya.moviekataloe.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {
lateinit var mainViewModel: MainViewModel
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_tv, container, false)
        mainViewModel = MainViewModel(view.context)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvAdapter = MediaAdapter()
        tvAdapter.mediaList = mainViewModel.getListTv()

        fTvRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = tvAdapter
        }
    }
}
