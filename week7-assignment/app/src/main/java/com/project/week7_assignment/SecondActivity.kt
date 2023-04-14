package com.project.week7_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // MainActivity 에서 데이터 받아오기
        val data = intent?.getStringExtra("inputdata") ?: ""

        findViewById<TextView>(R.id.textView).text = "${data}"

        // 데이터 되돌려주기
        val resultIntent = Intent()
        resultIntent.putExtra("resultdata", "result string")
        setResult(RESULT_OK, resultIntent)

        // Back 버튼 눌렀을 때 데이터 되돌려줌
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val resultIntent = Intent()
                resultIntent.putExtra("resultdata", "result string")
                setResult(RESULT_OK, resultIntent)
                finish()
            }

        })
    }
}
