package com.daya.moviekataloe.repo

import android.app.Application
import androidx.lifecycle.LiveData
import com.daya.moviekataloe.repo.room.FavoriteDao
import com.daya.moviekataloe.repo.room.FavoriteDatabase
import com.daya.moviekataloe.repo.room.MovieFavTable
import com.daya.moviekataloe.repo.room.TvFavTable
import java.util.concurrent.Executors

class MovTvRoomdbRepo(application: Application) {
    private var db: FavoriteDatabase = FavoriteDatabase.getInstance(application)!!
    private val favoriteDao: FavoriteDao
    private val singleExecuter = Executors.newSingleThreadExecutor()

    init {
        favoriteDao = db.favoriteDao()
    }

    fun getAllFavoriteMovie(): LiveData<List<MovieFavTable>> =
        db.favoriteDao().getAllFavoriteMovie()


    fun addFavoriteMovie(favTable: MovieFavTable) {
        singleExecuter.execute {
            db.favoriteDao().addFafoviteMovie(favTable)
        }
    }

    fun deleteFavoriteMovie(favTable: MovieFavTable) {
        singleExecuter.execute {
            db.favoriteDao().deleteFafoviteMovie(favTable)
        }
    }


    fun getAllFavoriteTv(): LiveData<List<TvFavTable>> =
        db.favoriteDao().getAllFavoriteTv()

    fun addFavoriteTv(favTable: TvFavTable) {
        singleExecuter.execute {
            db.favoriteDao().addFafoviteTv(favTable)
        }
    }

    fun deleteFavoriteTv(favTable: TvFavTable) {
        singleExecuter.execute {
            db.favoriteDao().deleteFafoviteTv(favTable)
        }
    }

}