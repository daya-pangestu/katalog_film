package com.daya.moviekataloe

import android.content.Context
import android.database.Cursor
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_IMAGE_LINK_MOVIE
import com.daya.moviekataloe.view.adapter.MediaAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


fun mapCursorToArrayList(cursor: Cursor): ArrayList<String> {
    val movieImageLink = ArrayList<String>()

    while (cursor.moveToNext()) {
        val imageLink = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_LINK_MOVIE))
        movieImageLink.add(imageLink)
    }
    return movieImageLink
}

fun getCurrentDate(): String {
    val todayDate = Calendar.getInstance().time
    val formatter = SimpleDateFormat("yyyy-MM-dd")

    return formatter.format(todayDate)
}

fun ImageView.loadImageWithGlide(url: String?, view: View? = null, context: Context? = null) {

    when {
        view != null -> Glide.with(view)
            .load(MediaAdapter.BASE_URL_IMAGE + url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(this)
        context != null -> Glide.with(context)
            .load(MediaAdapter.BASE_URL_IMAGE + url)
            .placeholder(R.drawable.ic_placeholder)
            .error(R.drawable.ic_broken_image_black_24dp)
            .into(this)
        else -> throw IllegalArgumentException("both context and view cannot be null")
    }
}