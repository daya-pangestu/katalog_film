package com.daya.moviekataloe.view.widget

import android.content.Context
import android.content.Intent
import android.database.Cursor
import android.graphics.Bitmap
import android.os.Binder
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import androidx.core.os.bundleOf
import com.bumptech.glide.Glide
import com.daya.moviekataloe.R
import com.daya.moviekataloe.mapCursorToArrayList
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.CONTENT_URI_MOVIE
import com.daya.moviekataloe.repo.room.DatabaseContract.Companion.TABLE_NAME_MOVIE
import com.daya.moviekataloe.view.adapter.MediaAdapter.Companion.BASE_URL_IMAGE
import org.jetbrains.anko.AnkoLogger

internal class StackRemoteViewsFactoryMovie(private val mContext: Context) :
    RemoteViewsService.RemoteViewsFactory, AnkoLogger {

    private val listMovie = mutableListOf<String>()

    override fun onCreate() {
    }


    override fun onDataSetChanged() {
        var cursorMovie: Cursor? = null

        cursorMovie?.close()

        val identityToken = Binder.clearCallingIdentity()
        cursorMovie = mContext.contentResolver.query(
            CONTENT_URI_MOVIE,
            arrayOf(TABLE_NAME_MOVIE),
            null,
            null,
            null
        )

        cursorMovie?.let {
            listMovie.addAll(mapCursorToArrayList(it))
        }
        Binder.restoreCallingIdentity(identityToken)
    }

    override fun onDestroy() {

    }
    override fun getCount(): Int = listMovie.size

    override fun getViewAt(position: Int): RemoteViews {
        val rv = RemoteViews(mContext.packageName, R.layout.widget_item_movie)

        try {


            val movieBitmap: Bitmap = Glide.with(mContext)
                .asBitmap()
                .load(BASE_URL_IMAGE + listMovie[position])
                .submit()
                .get()

            rv.setImageViewBitmap(R.id.widgetItemImageViewMovie, movieBitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        val extras = bundleOf(
            StackWidgetMovie.EXTRA_ITEM to position
        )
        val fillInIntent = Intent()
        fillInIntent.putExtras(extras)

        rv.setOnClickFillInIntent(R.id.widgetItemImageViewMovie, fillInIntent)
        return rv
    }

    override fun getLoadingView(): RemoteViews? = null


    override fun hasStableIds(): Boolean = false


    override fun getItemId(p0: Int): Long = 0

    override fun getViewTypeCount(): Int = 1

}