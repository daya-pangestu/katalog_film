package com.daya.moviekataloe.view


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.tv.TvModel
import com.daya.moviekataloe.view.adapter.MediaAdapter
import com.daya.moviekataloe.view.adapter.MediaAdapter.Companion.TYPE_TV
import com.daya.moviekataloe.viewmodel.MediaViewModel
import kotlinx.android.synthetic.main.fragment_tv.*

class TvFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_tv, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvVModel = ViewModelProviders.of(activity!!).get(MediaViewModel::class.java)

        tvVModel.getTv().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                initRecyclerview(it, view.context)
            }
        })
    }


    private fun initRecyclerview(tvModel: TvModel, context: Context) {
        val movieAdapter = MediaAdapter(TYPE_TV)
        movieAdapter.tvModel = tvModel
        fTvRecyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = movieAdapter
        }
        fTvprogressBar.visibility = View.GONE
    }
}

