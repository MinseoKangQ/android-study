package com.first.week6_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val button = findViewById<Button>(R.id.button)
        val radioDog = findViewById<RadioButton>(R.id.radioDog)
        val radioCat = findViewById<RadioButton>(R.id.radioCat)

        button.setOnClickListener {
            if(radioDog.isChecked) { textView.text = radioDog.text }
            if(radioCat.isChecked) { textView.text = radioCat.text }
        }
    }


}