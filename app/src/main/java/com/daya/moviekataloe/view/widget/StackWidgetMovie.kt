package com.daya.moviekataloe.view.widget

import android.app.PendingIntent
import android.appwidget.AppWidgetManager
import android.appwidget.AppWidgetProvider
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.widget.RemoteViews
import android.widget.Toast
import androidx.core.net.toUri
import com.daya.moviekataloe.R


class StackWidgetMovie : AppWidgetProvider() {

    override fun onUpdate(
        context: Context,
        appWidgetManager: AppWidgetManager,
        appWidgetIds: IntArray
    ) {
        // There may be multiple widgets active, so update all of them
        for (appWidgetId in appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId)
        }
    }

    override fun onEnabled(context: Context) {
        // Enter relevant functionality for when the first widget is created
    }

    override fun onDisabled(context: Context) {
        // Enter relevant functionality for when the last widget is disabled
    }

    companion object {

        private const val TOAST_ACTION = "action"
        const val EXTRA_ITEM = "extra_item"
        private const val REFRESH_ACTION = "REFRESH"


        private fun updateAppWidget(
            context: Context, appWidgetManager: AppWidgetManager,
            appWidgetId: Int
        ) {
            val intent = Intent(context, StackWidgetMovieService::class.java)
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()

            val views = RemoteViews(context.packageName, R.layout.stack_widget_movie)
            views.setRemoteAdapter(R.id.stackViewMovie, intent)
            views.setEmptyView(R.id.stackViewMovie, R.id.empty_view)

            val toastIntent = Intent(context, StackWidgetMovie::class.java)
            toastIntent.action = TOAST_ACTION
            toastIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId)
            intent.data = intent.toUri(Intent.URI_INTENT_SCHEME).toUri()
            val toastPendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                toastIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )


            views.setPendingIntentTemplate(R.id.stackViewMovie, toastPendingIntent)

            appWidgetManager.updateAppWidget(appWidgetId, views)
        }

    }

    override fun onReceive(context: Context?, intent: Intent?) {
        super.onReceive(context, intent)
        if (intent?.action != null) {
            if (intent.action == TOAST_ACTION) {
                val viewIndex = intent.getIntExtra(EXTRA_ITEM, 0)
                Toast.makeText(context, "Touched view $viewIndex", Toast.LENGTH_SHORT).show()
            }

            if (intent.action == REFRESH_ACTION) {
                // refresh all your widgets
                val mgr = AppWidgetManager.getInstance(context)
                val cn = context?.let { ComponentName(it, StackWidgetMovie::class.java) }
                mgr.notifyAppWidgetViewDataChanged(
                    mgr.getAppWidgetIds(cn),
                    R.id.widgetItemImageViewMovie
                )
            }
        }
    }
}