package com.daya.moviekataloe.repo.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_DESCRIPTION_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_ID_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_IMAGE_LINK_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_NAME_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.TABLE_NAME_MOVIE
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME_MOVIE)
data class MovieFavTable(
    @PrimaryKey
    @ColumnInfo(index = true, name = COLUMN_ID_MOVIE)
    val id: Int?,

    @ColumnInfo(name = COLUMN_NAME_MOVIE)
    val title: String?,

    @ColumnInfo(name = COLUMN_DESCRIPTION_MOVIE)
    val description: String?,

    @ColumnInfo(name = COLUMN_IMAGE_LINK_MOVIE)
    val imageLink: String?
) : Parcelable