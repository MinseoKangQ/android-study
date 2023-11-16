package com.android.firebase

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.storage.storage

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val uId = intent.getStringExtra("uId")
        findViewById<TextView>(R.id.uId).text = uId.toString()

        findViewById<Button>(R.id.signOutBtn).setOnClickListener {
            Firebase.auth.signOut()
            startActivity(
                Intent(this, LoginActivity::class.java)
            )
        }

        // 이미지 표시
        displayImage()

    }

    fun displayImage() {
        val imageRef = Firebase.storage.getReferenceFromUrl(
            "gs://firstfirebase-ff3ba.appspot.com/profile.png"
        )

        val view = findViewById<ImageView>(R.id.imageView)
        imageRef?.getBytes(Long.MAX_VALUE)?.addOnSuccessListener {
            val bmp = BitmapFactory.decodeByteArray(it, 0, it.size)
            view.setImageBitmap(bmp)
        }?.addOnFailureListener{ }

    }

}