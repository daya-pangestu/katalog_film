package com.daya.moviekataloe.repo.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoriteDao {

    @Query("SELECT * FROM MovieFavTable")
    fun getAllFavoriteMovie(): LiveData<List<MovieFavTable>>

    @Insert
    fun addFavoriteMovie(movieFav: MovieFavTable)

    @Delete
    fun deleteFavoriteMovie(movieFav: MovieFavTable)

    @Query("SELECT * FROM MovieFavTable WHERE id = :id ")
    fun isMovieFavorite(id: Int): LiveData<MovieFavTable>

    @Query("SELECT * FROM MovieFavTable")
    fun getAllFavMovie(): LiveData<List<MovieFavTable>>

    @Query("SELECT * FROM TvFavTable")
    fun getAllFavoriteTv(): LiveData<List<TvFavTable>>

    @Insert
    fun addFavoriteTv(tvFav: TvFavTable)

    @Delete
    fun deleteFavoriteTv(tvFav: TvFavTable)

    @Query("SELECT * FROM TvFavTable WHERE id = :id ")
    fun isTvFavorite(id: Int): LiveData<TvFavTable>

    @Query("SELECT * FROM TvFavTable")
    fun getAllFavTv(): LiveData<List<TvFavTable>>

}