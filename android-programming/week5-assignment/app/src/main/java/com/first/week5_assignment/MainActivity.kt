package com.first.week5_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val likeBtn = findViewById<Button>(R.id.button)
        likeBtn.setOnClickListener { println("Like clicked!") }
    }
}