package com.example.mystudyproject.appcomponents.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlin.concurrent.thread

class MyBackgroundService : Service() {

    override fun onCreate() {
        println("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        thread {
            repeat(30) {
                Thread.sleep(1000)
                println("SERVICE")
            }
        }
        stopSelf()

        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}