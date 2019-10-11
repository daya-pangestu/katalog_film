package com.mobile.favorit

import android.net.Uri
import android.provider.BaseColumns

class DatabaseContract {

    companion object {
        //table movie favorite
        const val TABLE_NAME_MOVIE = "moviefav"
        const val COLUMN_ID_MOVIE = BaseColumns._ID
        const val COLUMN_NAME_MOVIE = "title"
        const val COLUMN_DESCRIPTION_MOVIE = "description"
        const val COLUMN_IMAGE_LINK_MOVIE = "image_link"

        //table tv favorite
        const val TABLE_NAME_TV = "tvfav"
        const val COLUMN_ID_TV = BaseColumns._ID
        const val COLUMN_NAME_TV = "title"
        const val COLUMN_DESCRIPTION_TV = "description"
        const val COLUMN_IMAGE_LINK_TV = "image_link"

        private const val AUTHORITY = "com.daya.moviekataloe"
        private const val SCHEME = "content"

        val CONTENT_URI_MOVIE: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME_MOVIE)
            .build()

        val CONTENT_URI_TV: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME_TV)
            .build()
    }
}