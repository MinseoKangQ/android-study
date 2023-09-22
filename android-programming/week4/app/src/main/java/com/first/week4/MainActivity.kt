package com.first.week4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val txtView = findViewById<TextView>(R.id.txtView)
        txtView.setOnClickListener { println("##### Hello, world! #####") }
    }
}