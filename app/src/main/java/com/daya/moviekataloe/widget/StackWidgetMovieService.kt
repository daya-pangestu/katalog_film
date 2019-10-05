package com.daya.moviekataloe.widget

import android.content.Intent
import android.widget.RemoteViewsService

class StackWidgetMovieService : RemoteViewsService() {
    override fun onGetViewFactory(p0: Intent?): RemoteViewsFactory =
        StackRemoteViewsFactoryMovie(this.applicationContext)
}