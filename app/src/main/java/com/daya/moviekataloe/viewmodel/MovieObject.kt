package com.daya.moviekataloe.viewmodel

import android.content.Context
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.MediaModel

class MovieObject {
    companion object {

    fun getMovie(context : Context): ArrayList<MediaModel> {

        val nContext  = context.applicationContext

        val data: ArrayList<MediaModel>  = arrayListOf()
        val judulArray = nContext.resources.obtainTypedArray(R.array.judul)
        val deskripsiArray = nContext.resources.obtainTypedArray(R.array.desc)
        val posterPositionArray = nContext.resources.obtainTypedArray(R.array.poster)
        var count = 0
        while (judulArray.getString(count) != null) {

            val posterPosition = posterPositionArray.getResourceId(count,0)
            val sjudul = judulArray.getString(count)
            val sdeskripsi = deskripsiArray.getString(count)
            val movieModel = MediaModel(
                judul = sjudul,
                poster = posterPosition,
                deskripsi = sdeskripsi
            )
            data.add(movieModel)
            count++

        }

        judulArray.recycle()
        deskripsiArray.recycle()
        posterPositionArray.recycle()
        return data
    }
    }
}
