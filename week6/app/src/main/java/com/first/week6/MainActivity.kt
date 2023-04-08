package com.first.week6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import com.first.week6_assignment.R
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        val radioDog = findViewById<RadioButton>(R.id.radioDog)
        val radioCat = findViewById<RadioButton>(R.id.radioCat)

        val editText = findViewById<EditText>(R.id.editTextTextPersonName)
        val textView2 = findViewById<TextView>(R.id.textView2)

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        button.setOnClickListener {
            val pet = "Dog:${radioDog.isChecked}, Cat:${radioCat.isChecked}"
            Snackbar.make(it, pet, Snackbar.LENGTH_SHORT).show()

            textView2.text = editText.text

            when(radioGroup.checkedRadioButtonId) {
                R.id.radioCat -> println("radioCat")
                R.id.radioDog -> println("radioDog")
            }
        }
    }
}