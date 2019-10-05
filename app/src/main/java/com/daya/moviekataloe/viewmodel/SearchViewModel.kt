package com.daya.moviekataloe.viewmodel

import androidx.lifecycle.ViewModel
import com.daya.moviekataloe.repo.MovTvNetworkRepo

class SearchViewModel : ViewModel() {
    val repo by lazy { MovTvNetworkRepo() }

    fun getMoviBysearch(query: String) = repo.getMovieBysearch(query)

    fun getTvBySearch(query: String) = repo.getTvBySearch(query)

}