package com.cartrack.portal.user.view

import android.app.Application
import android.content.Context

class CarTrackApp: Application() {

    companion object {
        lateinit var appContext: Context
        fun getContext(): Context {
            return appContext
        }

        fun setContext(context: Context) {
            appContext = context
        }
    }

    override fun onCreate() {
        super.onCreate()
        setContext(applicationContext)
    }

}