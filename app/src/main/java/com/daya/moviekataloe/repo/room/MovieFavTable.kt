package com.daya.moviekataloe.repo.room

import androidx.room.Entity

@Entity
data class MovieFavTable(val title: String, val description: String, val imageLink: String)