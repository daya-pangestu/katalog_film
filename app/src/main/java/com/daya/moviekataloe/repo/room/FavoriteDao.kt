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
    fun addFafoviteMovie(movieFav: MovieFavTable)

    @Delete
    fun deleteFafoviteMovie(movieFav: MovieFavTable)

    @Query("SELECT * FROM MovieFavTable WHERE id = :id ")
    fun isMovieFavorite(id: Int): LiveData<MovieFavTable>


    @Query("SELECT * FROM TvFavTable")
    fun getAllFavoriteTv(): LiveData<List<TvFavTable>>

    @Insert
    fun addFafoviteTv(tvFav: TvFavTable)

    @Delete
    fun deleteFafoviteTv(tvFav: TvFavTable)

    @Query("SELECT * FROM TvFavTable WHERE id = :id ")
    fun isTvFavorite(id: Int): LiveData<TvFavTable>
}