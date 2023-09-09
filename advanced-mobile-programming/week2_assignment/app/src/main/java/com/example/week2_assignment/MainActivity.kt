package com.example.week2_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup

class MainActivity : AppCompatActivity() {

    private lateinit var myView: MyView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myView = findViewById(R.id.view) // myView 변수 초기화

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener{ _, checkedId ->
            when (checkedId) {
                R.id.radioRect -> myView.drawRectangle()
                R.id.radioCircle -> myView.drawCircle()
            }
        }
    }
}