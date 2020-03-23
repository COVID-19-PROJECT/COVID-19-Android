package com.gt.quedateencasa

import android.app.Application
import com.facebook.appevents.AppEventsLogger

class QuedateEnCasaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppEventsLogger.activateApp(this)
    }
}