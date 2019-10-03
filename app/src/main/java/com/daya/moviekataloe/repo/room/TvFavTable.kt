package com.daya.moviekataloe.repo.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_DESCRIPTION_TV
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_ID_TV
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_IMAGE_LINK_TV
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_NAME_TV
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.TABLE_NAME_TV
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = TABLE_NAME_TV)
data class TvFavTable(

    @ColumnInfo(name = COLUMN_ID_TV)
    @PrimaryKey val id: Int,


    @ColumnInfo(name = COLUMN_NAME_TV)
    val title: String,

    @ColumnInfo(name = COLUMN_DESCRIPTION_TV)
    val description: String,

    @ColumnInfo(name = COLUMN_IMAGE_LINK_TV)
    val imageLink: String
) : Parcelable