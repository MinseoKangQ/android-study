package com.android.firebase

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.storage

class MainActivity : AppCompatActivity() {

//    private lateinit var seasonText: TextView
//    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 사용자 UID 가져오기
        val uId = intent.getStringExtra("uId")
        findViewById<TextView>(R.id.uIdText).text = uId.toString()

        // 로그아웃
        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            Firebase.auth.signOut()
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

        // Remote Config
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1 // For test purpose only, 3600 seconds for production
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config)

        var seasonText = findViewById<TextView>(R.id.seasonText)

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { // it: task
                val season = remoteConfig.getString("season")
                seasonText.text = "${season}"
                displayImage(season) // 이미지 표시
            }

        // 새로고침 버튼
        val refreshBtn = findViewById<Button>(R.id.refreshBtn)
        refreshBtn.setOnClickListener {
            refreshSeason()
        }

    }

    private fun displayImage(season: String) {
        val imageRef = when (season) {
            "spring" -> Firebase.storage.getReferenceFromUrl("gs://firstfirebase-ff3ba.appspot.com/spring.JPG")
            "summer" -> Firebase.storage.getReferenceFromUrl("gs://firstfirebase-ff3ba.appspot.com/summer.JPG")
            "fall" -> Firebase.storage.getReferenceFromUrl("gs://firstfirebase-ff3ba.appspot.com/fall.JPG")
            "winter" -> Firebase.storage.getReferenceFromUrl("gs://firstfirebase-ff3ba.appspot.com/winter.JPG")
            else -> null
        }
        imageRef?.let { show(it) }
    }

    private fun show(imageRef: StorageReference) {
        imageRef.getBytes(Long.MAX_VALUE).addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            var imageView = findViewById<ImageView>(R.id.imageView)
            imageView.setImageBitmap(bmp)
        }.addOnFailureListener { }
    }

    private fun refreshSeason() {
        val remoteConfig = Firebase.remoteConfig
        var seasonText = findViewById<TextView>(R.id.seasonText)

        // 다시 Remote Config에서 값을 가져오기
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val season = remoteConfig.getString("season")

                    seasonText.text = season
                    displayImage(season) // 이미지 업데이트
                } else { }
            }
    }

}