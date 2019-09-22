package com.daya.moviekataloe

import android.app.Application
import com.facebook.stetho.Stetho

class MovieKataloe : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
    }
}