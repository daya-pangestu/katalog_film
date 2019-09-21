package com.daya.moviekataloe.viewmodel

import androidx.lifecycle.ViewModel
import com.daya.moviekataloe.repo.MovTvNetworkRepo

class MediaViewModel : ViewModel() {
    private var networkRepo: MovTvNetworkRepo = MovTvNetworkRepo()

    fun getMovie() = networkRepo.getMovie()

    fun getTv() = networkRepo.getTv()


}