package com.sytyy.pecode.test.notification

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.sytyy.pecode.test.R
import com.sytyy.pecode.test.view.MainActivity


class MainNotification(var notificationId: Int) {

    val CHANNEL_NAME = "channel name"
    var channelId = "channel ID $notificationId"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_HIGH
            )
            val manager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }


    @SuppressLint("ResourceAsColor", "LaunchActivityFromNotification", "UnspecifiedImmutableFlag")
    fun createNotification(context: Context, pageNumber: Int): Notification {
        val intent = Intent(context, MainActivity::class.java).apply {

        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        return NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.small_icon)
            .setContentTitle("You create a notification")
            .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL)
            .setContentText("Notification $pageNumber")
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)
            .build()
    }


}