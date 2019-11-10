package com.daya.moviekataloe.service

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.daya.moviekataloe.R
import com.daya.moviekataloe.model.NotificationItem
import com.daya.moviekataloe.model.movie.MovieModel
import com.daya.moviekataloe.view.MainActivity
import com.daya.moviekataloe.view.todayrelease.TodayReleaseActivity
import com.daya.moviekataloe.viewmodel.getlistMovieToday
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*


class AlarmDailyRepeatingReceiver : BroadcastReceiver() {

    companion object {
        const val TYPE_REMINDER_AT_7 = "reminder7"
        const val TYPE_REMINDER_AT_8 = "reminder8"
        const val EXTRA_MESSAGE = "message"
        const val EXTRA_TYPE = "type"

        private const val ID_AT_7 = 100
        private const val ID_AT_8 = 101

    }

    override fun onReceive(context: Context, intent: Intent) {
        val type = intent.getStringExtra(EXTRA_TYPE)
        val message = intent.getStringExtra(EXTRA_MESSAGE)
        val notifId = if (type.equals(TYPE_REMINDER_AT_7, ignoreCase = true)) ID_AT_7 else ID_AT_8

        message?.let {

            if (notifId == ID_AT_7) {
                showAlarmNotification(context, it, notifId)
            } else {
                CoroutineScope(IO).launch {
                    showAlarmNotification8(context)
                }

            }
        }
    }

    fun setAlarmAt7(context: Context, date: String) {
        val DATE_FORMAT = "yyyy-MM-dd"
        val TIME_FORMAT = "HH:mm"
        val message: String = context.getString(R.string.message_alarm_7)
        val type = TYPE_REMINDER_AT_7
        val time = "07:00"

        if (isDateInvalid(date, DATE_FORMAT) || isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmDailyRepeatingReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_AT_7, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )

    }

    fun setAlarmAt8(context: Context, date: String) {
        val DATE_FORMAT = "yyyy-MM-dd"
        val TIME_FORMAT = "HH:mm"
        val type = TYPE_REMINDER_AT_8
        val time = "08:00"

        val message = context.getString(R.string.message_alarm_8)

        if (isDateInvalid(date, DATE_FORMAT) || isDateInvalid(time, TIME_FORMAT)) return
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmDailyRepeatingReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)

        val timeArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val calendar = Calendar.getInstance()

        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timeArray[0]))
        calendar.set(Calendar.MINUTE, Integer.parseInt(timeArray[1]))
        calendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, ID_AT_8, intent, 0)
        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    private fun isDateInvalid(date: String, format: String): Boolean {
        return try {
            val df = SimpleDateFormat(format, Locale.getDefault())
            df.isLenient = false
            df.parse(date)
            false
        } catch (e: ParseException) {
            true
        }
    }

    private fun showAlarmNotification(context: Context, message: String, notifId: Int) {
        val CHANNEL_ID = "Channel_1"
        val CHANNEL_NAME = "AlarmManager channel"
        val requestID = System.currentTimeMillis().toInt()
        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = if (notifId == ID_AT_7) {
            Intent(context, MainActivity::class.java)
        } else {
            Intent(context, TodayReleaseActivity::class.java)

        }

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent =
            PendingIntent.getActivity(context, requestID, intent, PendingIntent.FLAG_ONE_SHOT)


        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_history_black_36dp)
            .setContentTitle("daily reminder")
            .setContentIntent(pendingIntent)
            .setContentText(message)
            .setColor(ContextCompat.getColor(context, android.R.color.transparent))
            .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
            .setSound(alarmSound)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.enableVibration(true)
            channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
            builder.setChannelId(CHANNEL_ID)
            notificationManagerCompat.createNotificationChannel(channel)
        }
        val notification = builder.build()
        notificationManagerCompat.notify(notifId, notification)
    }

    private suspend fun showAlarmNotification8(context: Context) {
        val CHANNEL_ID = "Channel_2"
        val CHANNEL_NAME = "AlarmManager channel"
        val GROUP_KEY_EMAILS = "group_key"
        val MAX_NOTIF = 3
        val requestID = System.currentTimeMillis().toInt()
        var idNotif = 0

        val listMovie = CoroutineScope(IO).async {
            getlistMovie()
        }

        val data = listMovie.await()

        val listNotif = arrayListOf<NotificationItem>()

        data?.results?.let {
            it.forEach { result ->
                listNotif.add(NotificationItem(result.id, result.title))
            }
        }

        val notificationManagerCompat =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val intent = Intent(context, TodayReleaseActivity::class.java)

        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

        val pendingIntent =
            PendingIntent.getActivity(context, requestID, intent, PendingIntent.FLAG_ONE_SHOT)
        var builder: NotificationCompat.Builder
        listNotif.let {

            for (i in 0..3) {

                if (idNotif < MAX_NOTIF) {
                    builder = NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_history_black_36dp)
                        .setContentTitle("new Movie Relese Today")
                        .setContentIntent(pendingIntent)
                        .setContentText("${it[idNotif].title},released today")
                        .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                        .setGroup(GROUP_KEY_EMAILS)
                        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                        .setSound(alarmSound)
                        .setAutoCancel(true)
                    idNotif++
                } else {
                    val inboxStyle = NotificationCompat.InboxStyle()
                        .addLine("New Movie Today :" + listNotif[idNotif].title)
                        .addLine("New Movie Today :" + listNotif[idNotif - 1].title)
                        .addLine("New Movie Today :" + listNotif[idNotif - 2].title)
                        .setBigContentTitle("$idNotif new Movie")
                        .setSummaryText("new released movie today")
                    builder = NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.drawable.ic_history_black_36dp)
                        .setContentTitle("new Movie Relese Today")
                        .setContentIntent(pendingIntent)
                        .setContentText(if (idNotif >= 3) "3+ movie,released today" else "$idNotif movie, released today")
                        .setColor(ContextCompat.getColor(context, android.R.color.transparent))
                        .setGroup(GROUP_KEY_EMAILS)
                        .setGroupSummary(true)
                        .setVibrate(longArrayOf(1000, 1000, 1000, 1000, 1000))
                        .setSound(alarmSound)
                        .setStyle(inboxStyle)
                        .setAutoCancel(true)

                    idNotif++
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    val channel = NotificationChannel(
                        CHANNEL_ID,
                        CHANNEL_NAME,
                        NotificationManager.IMPORTANCE_DEFAULT
                    )
                    channel.enableVibration(true)
                    channel.vibrationPattern = longArrayOf(1000, 1000, 1000, 1000, 1000)
                    builder.setChannelId(CHANNEL_ID)
                    notificationManagerCompat.createNotificationChannel(channel)
                }
                val notification = builder.build()
                notificationManagerCompat.notify(idNotif, notification)
            }
        }
    }

    fun cancelAlarm(context: Context, type: String) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, AlarmDailyRepeatingReceiver::class.java)
        val requestCode =
            if (type.equals(TYPE_REMINDER_AT_7, ignoreCase = true)) ID_AT_7 else ID_AT_8
        val pendingIntent = PendingIntent.getBroadcast(context, requestCode, intent, 0)
        pendingIntent.cancel()
        alarmManager.cancel(pendingIntent)
    }


    suspend fun getlistMovie(): MovieModel? {
        return getlistMovieToday()
    }
}
