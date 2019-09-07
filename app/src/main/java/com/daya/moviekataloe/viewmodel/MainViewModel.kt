package com.daya.moviekataloe.viewmodel

import android.content.Context
import com.daya.moviekataloe.model.MediaModel

class MainViewModel(private val context: Context) {

    fun getListMovie() :ArrayList<MediaModel> = MovieObject.getMovie(context)

    fun getListTv() : ArrayList<MediaModel> =  TvObject.getTv(context)
}