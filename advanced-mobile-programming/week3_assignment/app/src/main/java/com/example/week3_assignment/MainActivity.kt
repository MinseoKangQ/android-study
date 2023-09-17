package com.example.week3_assignment

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.widget.Button
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 권한 관련 코드
        requestSinglePermission(Manifest.permission.POST_NOTIFICATIONS)

        // 채널 만들기
        createdNotificationChannel()

        // 버튼 누를 때 showNotification 호출
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            showNotification()
        }
    }

    // 채널 만들기
    private val channelID = "default"
    private var myNotificationID = 1
    private fun createdNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelID, "default channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            channel.description = "description text of this channel."
            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    // 단순 알림 생성
    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, channelID)
        builder.setSmallIcon(R.mipmap.ic_launcher)
        builder.setContentTitle("따라하기 실습")
        builder.setContentText("따라하기 내용")
        builder.setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val notification = builder.build()

        if(checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(this)
                .notify(myNotificationID, notification) // 아이디 같으면 알림 덮어짐
        }
    }

    // 권한 관련 코드
    private fun requestSinglePermission(permission: String) {
        if (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED)
            return
        val requestPermLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it == false) {
                AlertDialog.Builder(this).apply {
                    setTitle("Warning")
                    setMessage(getString(R.string.no_permission, permission))
                }.show()
            }
        }
        if (shouldShowRequestPermissionRationale(permission)) {
            AlertDialog.Builder(this).apply {
                setTitle("Reason")
                setMessage(getString(R.string.req_permission_reason, permission))
                setPositiveButton("Allow") { _, _ -> requestPermLauncher.launch(permission)}
                setNegativeButton("Deny") { _, _ -> }
            }.show()
        } else {
            requestPermLauncher.launch(permission)
        }
    }
}