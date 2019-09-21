package com.daya.moviekataloe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.daya.moviekataloe.repo.MovTvRoomdbRepo
import com.daya.moviekataloe.repo.room.MovieFavTable
import com.daya.moviekataloe.repo.room.TvFavTable

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val roomDb = MovTvRoomdbRepo(application)

    fun getAllFavoriteMovie(): LiveData<List<MovieFavTable>> =
        roomDb.getAllFavoriteMovie()

    fun addFavoriteMovie(favTable: MovieFavTable) {
        roomDb.addFavoriteMovie(favTable)
    }

    fun deleteFavoriteMovie(favTable: MovieFavTable) {
        roomDb.deleteFavoriteMovie(favTable)
    }

    fun getAllFavoriteTv(): LiveData<List<TvFavTable>> =
        roomDb.getAllFavoriteTv()

    fun addFavoriteTv(favTable: TvFavTable) {
        roomDb.addFavoriteTv(favTable)
    }

    fun deleteFavoriteTv(favTable: TvFavTable) {
        roomDb.deleteFavoriteTv(favTable)
    }

}