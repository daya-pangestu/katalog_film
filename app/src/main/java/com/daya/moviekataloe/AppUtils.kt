package com.daya.moviekataloe

import android.database.Cursor
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.COLUMN_IMAGE_LINK_MOVIE

fun mapCursorToArrayList(cursor: Cursor): ArrayList<String> {
    val movieImageLink = ArrayList<String>()

    while (cursor.moveToNext()) {
        val imageLink = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_IMAGE_LINK_MOVIE))
        movieImageLink.add(imageLink)
    }
    return movieImageLink
}