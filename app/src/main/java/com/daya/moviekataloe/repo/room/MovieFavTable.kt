package com.daya.moviekataloe.repo.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MovieFavTable(
    @PrimaryKey val id: Int, val title: String,
    val description: String,
    val imageLink: String
)