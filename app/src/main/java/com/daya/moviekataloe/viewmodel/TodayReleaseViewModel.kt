package com.daya.moviekataloe.viewmodel

import androidx.lifecycle.ViewModel
import com.daya.moviekataloe.repo.MovTvNetworkRepo

class TodayReleaseViewModel : ViewModel() {
    private val repo by lazy { MovTvNetworkRepo() }

    fun getMovieToday() = repo.getTodayRelMovie()

}