package com.example.mystudyproject.appcomponents.utils


import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi

fun Context.getPendingIntent(activity: Class<*>): PendingIntent {
    return Intent(this, activity).let { notificationIntent ->
        PendingIntent.getActivity(
            this, 1, notificationIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}

fun Context.createNotificationChannel(
    channelId: String = "channelId",
    channelName: String = "channelName",
) =
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        createNotificationChannel(this, channelId, channelName)
    } else {
        ""
    }

@RequiresApi(Build.VERSION_CODES.O)
private fun Context.createNotificationChannel(
    context: Context,
    channelId: String = "channelId",
    channelName: String = "channelName",
): String {
    val chan = NotificationChannel(
        channelId,
        channelName, NotificationManager.IMPORTANCE_NONE
    )
    chan.lightColor = Color.BLUE
    chan.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
    val service = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (service.notificationChannels.all { it.id != channelId }) {
        service.createNotificationChannel(chan)
    }
    return channelId
}