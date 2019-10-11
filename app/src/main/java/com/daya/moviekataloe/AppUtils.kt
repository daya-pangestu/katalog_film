package com.daya.moviekataloe

import android.database.Cursor
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_IMAGE_LINK_MOVIE
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
    val todayString = formatter.format(todayDate)

    return todayString
}