package com.example.myapp

import android.app.Application
import android.content.Context


class MyApplication : Application(){
    companion object {
        lateinit var  apiKey: String
        lateinit var applicationContext1:Context
            private set
    }

    override fun onCreate() {
        super.onCreate()

        applicationContext1 = this
        apiKey = "907d232eaa93fcce054f3599021123df"
    }
}