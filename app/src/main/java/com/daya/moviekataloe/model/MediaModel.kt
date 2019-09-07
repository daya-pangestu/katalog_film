package com.daya.moviekataloe.model

import android.os.Parcel
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MediaModel (
    var judul: String?,
    var deskripsi: String?,
    var poster: Int) : Parcelable {
}
