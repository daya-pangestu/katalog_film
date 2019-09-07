package com.daya.moviekataloe.viewmodel

import android.content.Context
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.MediaModel

class TvObject {
    companion object {
        fun getTv(context : Context): ArrayList<MediaModel> {

            val nContext  = context.applicationContext

            val data: ArrayList<MediaModel>  = arrayListOf()
            val judulArray = nContext.resources.obtainTypedArray(R.array.judul_tv)
            val deskripsiArray = nContext.resources.obtainTypedArray(R.array.deskripsitv)
            val posterPositionArray = nContext.resources.obtainTypedArray(R.array.poster_tv)
            var count = 0
            while (judulArray.getString(count) != null) {

                val posterPosition = posterPositionArray.getResourceId(count,0)
                val sjudul = judulArray.getString(count)
                val sdeskripsi = deskripsiArray.getString(count)
                val tvModel = MediaModel(
                    judul = sjudul,
                    poster = posterPosition,
                    deskripsi = sdeskripsi
                )
                data.add(tvModel)
                count++
            }

            judulArray.recycle()
            deskripsiArray.recycle()
            posterPositionArray.recycle()
            return data
        }
    }
    

   
}