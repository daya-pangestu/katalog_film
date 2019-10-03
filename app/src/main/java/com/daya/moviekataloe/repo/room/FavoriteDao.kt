package com.daya.moviekataloe.repo.room

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.TABLE_NAME_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.TABLE_NAME_TV

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM moviefav")
    fun getAllFavoriteMovie(): LiveData<List<MovieFavTable>>

    @Insert
    fun addFavoriteMovie(movieFav: MovieFavTable)

    @Delete
    fun deleteFavoriteMovie(movieFav: MovieFavTable)

    @Query("SELECT * FROM moviefav WHERE moviefav._id = :id ")
    fun isMovieFavorite(id: Int): LiveData<MovieFavTable>

    @Query("SELECT * FROM moviefav")
    fun getAllFavMovie(): LiveData<List<MovieFavTable>>

    @Query("SELECT * FROM $TABLE_NAME_TV")
    fun getAllFavoriteTv(): LiveData<List<TvFavTable>>

    @Insert
    fun addFavoriteTv(tvFav: TvFavTable)

    @Delete
    fun deleteFavoriteTv(tvFav: TvFavTable)

    @Query("SELECT * FROM $TABLE_NAME_TV WHERE _id = :id ")
    fun isTvFavorite(id: Int): LiveData<TvFavTable>

    @Query("SELECT * FROM  $TABLE_NAME_TV")
    fun getAllFavTv(): LiveData<List<TvFavTable>>


    ///////////////////////
    @Query("SELECT * FROM $TABLE_NAME_TV")
    fun getAllTVFavCursor(): Cursor

    @Query("SELECT * FROM $TABLE_NAME_MOVIE")
    fun getAllMovieFavCursor(): Cursor
}