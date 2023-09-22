package com.project.week12

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.preference.PreferenceManager
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // textView 클릭하면 SettingsActivity 시작하기
        val tv = findViewById<TextView>(R.id.textView)
        tv.setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

    }

    override fun onStart() {
        super.onStart()

        // 설정 값 읽어오기
        val tv = findViewById<TextView>(R.id.textView)
        val pref = PreferenceManager.getDefaultSharedPreferences(this)
        val signature = pref.getString("signature", "")
        Snackbar.make(this, tv, "signature ${signature}", Snackbar.LENGTH_SHORT).show()
    }
}