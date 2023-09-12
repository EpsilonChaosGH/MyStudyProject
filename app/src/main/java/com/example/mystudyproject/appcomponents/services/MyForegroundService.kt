package com.example.mystudyproject.appcomponents.services

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.example.mystudyproject.MainActivity
import com.example.mystudyproject.appcomponents.utils.NotificationFactory
import com.example.mystudyproject.appcomponents.utils.getPendingIntent
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class MyForegroundService : Service() {

    override fun onCreate() {
        println("onCreate")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("OTUS: ForegroundService onStartCommand")

        val notificationFactory = NotificationFactory(
            context = this,
            pendingIntent = getPendingIntent(MainActivity::class.java),
        )

        startForeground(1, notificationFactory.create(title = "Uploading", text = "Starting..."))

        thread {
            repeat(20) {
                Thread.sleep(1000)
                println("SERVICE")
                val notificationManager =
                    getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.notify(
                    1,
                    notificationFactory.create(text = "progress $it%"),
                )
            }
        }

        stopSelf()

        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? = null
}