package com.daya.moviekataloe.repo.retrofit

import com.daya.moviekataloe.getCurrentDate
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.model.tv.TvModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    companion object {
        const val API_KEY = "aabea87a9f335dd1669217b69d4088c0"
        const val LANGUAGE = "en-US"
    }
    @GET("movie")
    fun getListMovie(@Query("api_key") api_key: String = API_KEY, @Query("language") language: String = LANGUAGE): Call<MovieModel>

    @GET("tv")
    fun getListTv(@Query("api_key") api_key: String = API_KEY, @Query("language") language: String = LANGUAGE): Call<TvModel>


    @GET("movie")
    fun getListMovieTodayRel(
        @Query("api_key") api_key: String = API_KEY,
        @Query("primary_release_date.gte") primary_release_date_gte: String = getCurrentDate(),
        @Query("primary_release_date.lte") primary_release_date_lte: String = getCurrentDate()
    ): Call<MovieModel>

    @GET("tv")
    fun getListTvTodayRel(
        @Query("api_key") api_key: String = API_KEY,
        @Query("primary_release_date.gte") primary_release_date_gte: String = getCurrentDate(),
        @Query("primary_release_date.lte") primary_release_date_lte: String = getCurrentDate()
    ): Call<TvModel>

    @GET("movie")
    fun getSearchMovie(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("query") query: String
    )
            : Call<MovieModel>


    @GET("tv")
    fun getSearchTv(
        @Query("api_key") api_key: String = API_KEY,
        @Query("language") language: String = LANGUAGE,
        @Query("query") query: String
    ): Call<TvModel>

}