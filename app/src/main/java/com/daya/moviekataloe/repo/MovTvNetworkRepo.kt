package com.daya.moviekataloe.repo

import androidx.lifecycle.MutableLiveData
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.model.tv.TvModel
import com.daya.moviekataloe.repo.retrofit.ApiClient
import com.daya.moviekataloe.repo.retrofit.ApiService
import org.jetbrains.anko.AnkoLogger
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovTvNetworkRepo : AnkoLogger {

    private val service: ApiService by lazy { ApiClient.getRetrofitClient()!!.create(ApiService::class.java) }

    private val getListmovieService by lazy { service.getListMovie() }
    private val getListTvService by lazy { service.getListTv() }

    private var listMovieLiveData = MutableLiveData<MovieModel>()
    private var listTvLiveData = MutableLiveData<TvModel>()


    init {
        setMovie()
        setTv()
    }

    private fun setMovie() {

        getListmovieService.enqueue(object : Callback<MovieModel> {

            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    listMovieLiveData.value = body

                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) = t.printStackTrace()
        })
    }

    fun getMovie() = listMovieLiveData

    private fun setTv() {
        getListTvService.enqueue(object : Callback<TvModel> {

            override fun onResponse(call: Call<TvModel>, response: Response<TvModel>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    listTvLiveData.value = body
                }
            }

            override fun onFailure(call: Call<TvModel>, t: Throwable) = t.printStackTrace()
        })
    }

    fun getTv() = listTvLiveData


    fun getMovieBysearch(query: String): MutableLiveData<MovieModel> {
        val liveDataListMovie = MutableLiveData<MovieModel>()
        service.getSearchMovie(query = query).enqueue(object : Callback<MovieModel> {
            override fun onResponse(call: Call<MovieModel>, response: Response<MovieModel>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    liveDataListMovie.value = body
                }
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveDataListMovie
    }

    fun getTvBySearch(query: String): MutableLiveData<TvModel> {
        val liveDataListTv = MutableLiveData<TvModel>()
        service.getSearchTv(query = query).enqueue(object : Callback<TvModel> {
            override fun onResponse(call: Call<TvModel>, response: Response<TvModel>) {
                if (response.isSuccessful) {
                    val body = response.body()
                    liveDataListTv.value = body
                }
            }

            override fun onFailure(call: Call<TvModel>, t: Throwable) {
                t.printStackTrace()
            }
        })
        return liveDataListTv
    }


    suspend fun getListMovieToday(): MovieModel? = service.getListMovieTodayRel()

}