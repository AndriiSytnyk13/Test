package com.sytyy.pecode.test.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.sytyy.pecode.test.R

@RequiresApi(Build.VERSION_CODES.O)
class MainNotification(private val context: Context, var notificationId: Int = 0) {

    val CHANNEL_NAME = "channel name"
    val CHANNEL_ID = "channel ID"


    init {
        val channel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(channel)

    }

    @SuppressLint("ResourceAsColor", "InlinedApi")
    fun createNotification(pageNumber: Int): Notification =
        NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.small_icon)
            .setContentTitle("You create a notification")
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setContentText("Notification $pageNumber")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setColor(android.R.color.system_accent1_800)
            .setAutoCancel(true)
            .build()

}