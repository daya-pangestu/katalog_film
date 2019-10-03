package com.mobile.favorit

import android.app.Application
import android.content.Context
import android.provider.BaseColumns
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class SharedViewModel(application: Application) : AndroidViewModel(application) {
    private val context: Context = application
    private val mutableListMovie: MutableLiveData<List<MovieFavModel>> = MutableLiveData()
    private val mutableListTv: MutableLiveData<List<TvFavModel>> = MutableLiveData()

    init {
        setListMovie()
        setListTv()
    }

    private fun setListMovie() {

        val movieCursor = context.contentResolver.query(
            DatabaseContract.CONTENT_URI_MOVIE,
            arrayOf(DatabaseContract.TABLE_NAME_MOVIE),
            null,
            null,
            null
        )

        movieCursor?.let {
            val listMovie = mutableListOf<MovieFavModel>()
            if (it.count > 0) {
                while (it.moveToNext()) {
                    val id: Int = it.getInt(it.getColumnIndexOrThrow(BaseColumns._ID))
                    val name: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_NAME_TV))

                    val description: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_DESCRIPTION_TV))

                    val imageLink: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_IMAGE_LINK_TV))

                    Log.d("tag", "$id $name $description $imageLink")

                    listMovie.add(MovieFavModel(id, name, description, imageLink))
                }
                mutableListMovie.value = listMovie
            }
            movieCursor.close()
        }
    }

    fun getListMovie() = mutableListMovie

    private fun setListTv() {
        val tvCursor = context.contentResolver.query(
            DatabaseContract.CONTENT_URI_TV,
            arrayOf(DatabaseContract.TABLE_NAME_TV),
            null,
            null,
            null
        )

        tvCursor?.let {
            val listTv = mutableListOf<TvFavModel>()

            if (it.count > 0) {
                while (it.moveToNext()) {
                    val id: Int = it.getInt(it.getColumnIndexOrThrow(BaseColumns._ID))
                    val name: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_NAME_TV))

                    val description: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_DESCRIPTION_TV))

                    val imageLink: String =
                        it.getString(it.getColumnIndexOrThrow(DatabaseContract.COLUMN_IMAGE_LINK_TV))

                    Log.d("tag2", "$id $name $description $imageLink")

                    listTv.add(TvFavModel(id, name, description, imageLink))
                }
                mutableListTv.value = listTv
            }
            tvCursor.close()
        }
    }

    fun getLIstTv() = mutableListTv
}