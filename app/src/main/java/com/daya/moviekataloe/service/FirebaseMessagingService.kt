package com.daya.moviekataloe.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.daya.moviekataloe.R
import com.daya.moviekataloe.view.MainActivity
import com.daya.moviekataloe.view.todayrelease.TodayReleaseActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.debug

open class FirebaseMessagingService : FirebaseMessagingService(), AnkoLogger {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        debug("refresed token :$token")
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        remoteMessage.notification?.let {


            when (remoteMessage.from) {
                "/topics/jam7" -> sendNotificationAt7(it.body)
                "/topics/jam8" -> sendNotificationAt8(it.body)
                else -> sendNotificationAt7(it.body)
            }
        }

    }

    private fun sendNotificationAt7(messageBody: String?) {
        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = "jam7"

        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationBuilder.setChannelId(channelId)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()

        mNotificationManager.notify(0, notification)
    }

    private fun sendNotificationAt8(
        messageBody: String?
    ) {

        val channelId = getString(R.string.default_notification_channel_id)
        val channelName = "jam8"

        val intent = Intent(this, TodayReleaseActivity::class.java)


        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentText(messageBody)
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)

        val mNotificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

            notificationBuilder.setChannelId(channelId)

            mNotificationManager.createNotificationChannel(channel)
        }

        val notification = notificationBuilder.build()

        mNotificationManager.notify(0, notification)
    }
}