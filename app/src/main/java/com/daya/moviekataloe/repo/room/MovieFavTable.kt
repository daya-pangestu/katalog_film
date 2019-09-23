package com.daya.moviekataloe.repo.room

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class MovieFavTable(
    @PrimaryKey val id: Int, val title: String,
    val description: String,
    val imageLink: String
) : Parcelable