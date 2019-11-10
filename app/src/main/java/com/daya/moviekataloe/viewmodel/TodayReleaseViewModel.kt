package com.daya.moviekataloe.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.repo.MovTvNetworkRepo
import kotlinx.coroutines.async

private val repo by lazy { MovTvNetworkRepo() }

class TodayReleaseViewModel : ViewModel() {

    suspend fun getMovieToday(): MovieModel? {
        val list = viewModelScope.async {
            repo.getListMovieToday()
        }
        return list.await()
    }
}

suspend fun getlistMovieToday() = repo.getListMovieToday()