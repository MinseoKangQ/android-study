package com.example.servicelab

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MyService : Service() {

    var tickCount: Int = 0
        private set

    // bind
    private val binder = LocalBinder()

    inner class LocalBinder : Binder() {
        fun getService() = this@MyService
    }
    override fun onBind(intent: Intent): IBinder {
        return binder
    }

    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        // 포그라운드 시작해야함, Notification 있어야함 (이 클래스 제일 하단 부분에 작성)
        startForeground(notificationID, createNotification())

        val count = intent?.getIntExtra("init", 0) ?: 0

        tickCount  += count

        val resultIntent = Intent()
        resultIntent.putExtra("init", tickCount)

        CoroutineScope(Dispatchers.IO).apply {
            launch {
                for(i in 1 .. 10) {
                    delay(1000)
                    // 서비스 시작할 때 마다 count++
                    tickCount++
                    updateNotification(notificationID, createNotification(i*10))
                }
                stopSelf(startId)
            }
        }

        return super.onStartCommand(intent, flags, startId)

    }

    // Notification 관련
    private val channelID = "default"
    private val notificationID = 1

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(channelID, "default channel",
            NotificationManager.IMPORTANCE_DEFAULT)
        channel.description = "description text of this channel."
        NotificationManagerCompat.from(this).createNotificationChannel(channel)
    }
    private fun updateNotification(id: Int, notification: Notification) {
        NotificationManagerCompat.from(this).notify(id, notification)
    }
    private fun createNotification(progress: Int = 0) = NotificationCompat.Builder(this, channelID)
        .setContentTitle("Downloading")
        .setContentText("Downloading a file from a cloud")
        .setSmallIcon(R.drawable.ic_baseline_cloud_download_24)
        .setOnlyAlertOnce(true) // importance 에 따라 알림 소리가 날 때, 처음에만 소리나게 함
        .setProgress(100, progress, false)
        .build()

}