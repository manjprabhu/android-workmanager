package com.mnj.android_workmanager.workmanager

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import androidx.core.app.NotificationCompat
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.mnj.android_workmanager.R

class MyWorker(appContext: Context, workParams: WorkerParameters) : Worker(appContext, workParams) {

    private val RECIVE_TASK_KEY = "recive_massage"
    private val TASK_KEY = "tnput_TASK_KEY"

    override fun doWork(): Result {
        println("==>> .....worker thread running.....")
        doYourTask()
        val data1 = Data.Builder()
            .putString(RECIVE_TASK_KEY, " Receive data Successfully and Thank you ....")
            .build()
        return Result.success(data1);
    }

    private fun doYourTask() {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "notification_channel_id"

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Worker Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        )

        // Configure the notification channel.
        notificationChannel.description = "Channel description"
        notificationChannel.enableLights(true)
        notificationChannel.lightColor = Color.BLUE
        notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
        notificationChannel.enableVibration(true)
        notificationManager.createNotificationChannel(notificationChannel)

        val notificationBuilder =
            NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)

        notificationBuilder.setAutoCancel(false)
            .setDefaults(Notification.DEFAULT_ALL)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setContentTitle("Work Manager Sample")
            .setContentText("Hello Everyone !!!")
            .setContentInfo("Work Manager triggered !!!")
        notificationManager.notify(1, notificationBuilder.build())
    }

    private fun showNotification(title: String?, desc: String?) {
        val notificationManager =
            applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val NOTIFICATION_CHANNEL_ID = "notification_channel_id"

        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            "Worker Notification",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
        val builder: NotificationCompat.Builder =
            NotificationCompat.Builder(applicationContext, "NOTIFICATION_CHANNEL_ID")
                .setContentTitle(title)
                .setContentText(desc)
                .setSmallIcon(R.mipmap.ic_launcher)
        notificationManager.notify(1, builder.build())
    }
}