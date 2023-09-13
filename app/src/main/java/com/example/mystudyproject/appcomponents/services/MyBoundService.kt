package com.example.mystudyproject.appcomponents.services

import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import com.example.mystudyproject.MainActivity
import com.example.mystudyproject.appcomponents.utils.NotificationFactory
import com.example.mystudyproject.appcomponents.utils.getPendingIntent
import java.util.concurrent.Executors
import kotlin.concurrent.thread

class MyBoundService : Service() {

    override fun onCreate() {
        println("onCreate")
        super.onCreate()
    }

    private var onProgress: ((Int) -> Unit)? = null

    inner class UploadBinder : Binder() {
        fun subscribeToProgress(onProgress: (Int) -> Unit) {
            this@MyBoundService.onProgress = onProgress
        }
    }

    override fun onBind(intent: Intent): IBinder {
        return UploadBinder()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        println("BOUND START")

        val notificationFactory = NotificationFactory(
            context = this,
            pendingIntent = getPendingIntent(MainActivity::class.java),
        )

        startForeground(1, notificationFactory.create(title = "Uploading", text = "Starting..."))

        thread {
            repeat(20) {
                Thread.sleep(1000)
                println("BOUND")
                onProgress?.invoke(it)
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
}