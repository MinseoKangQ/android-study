package com.project.week12_assignment

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.Dimension
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
    }

    override fun onStart() {

        super.onStart()

        // textView 가져오기
        val textView = findViewById<TextView>(R.id.textView)

        // 설정 값 읽어오기
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val name = pref.getString("name", "Hello World!")
        val size = pref.getString("size", "small")
        val isItalic = pref.getBoolean("italic", false)

        // 읽어온 설정값으로 설정하기
        textView.text = name
        if(!isItalic) { textView.setTypeface(textView.typeface, Typeface.NORMAL) }
        else { textView.setTypeface(textView.typeface, Typeface.ITALIC) }
        when (size) {
            "big" -> { textView.setTextSize(Dimension.SP, 20F)}
            "medium" -> { textView.setTextSize(Dimension.SP, 14F)}
            "small" -> { textView.setTextSize(Dimension.SP, 10F)}
        }

    }
}