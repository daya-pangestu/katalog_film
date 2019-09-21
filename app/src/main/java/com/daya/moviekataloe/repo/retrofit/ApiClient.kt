package com.daya.moviekataloe.repo.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/discover/"

        private var retrofit: Retrofit? = null

        fun getRetrofitClient(): Retrofit? {

            return if (retrofit == null) {
                Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            } else {
                retrofit
            }
        }

    }
}