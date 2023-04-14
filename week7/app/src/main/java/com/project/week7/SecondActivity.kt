package com.project.week7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.activity.OnBackPressedCallback

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("################## SecondActivity - onCreate ##################")
        setContentView(R.layout.activity_second)

        // MainActivity 에서 데이터 받아오기
        val msg = intent?.getStringExtra("userdata") ?: ""

        // 받아온 데이터를 TextView 에 출력
        findViewById<TextView>(R.id.textView).text = "${msg}"

        // 데이터 되돌려주기
        // 생성될 때 데이터 되돌려줌
        val resultIntent = Intent()
        resultIntent.putExtra("resultdata", "result string")
        setResult(RESULT_OK, resultIntent)

        // Back 버튼 눌렀을 때 데이터 되돌려줌
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                val resultIntent = Intent()
                resultIntent.putExtra("resultdata", "result string")
                setResult(RESULT_OK, resultIntent)
                finish() // 액티비티 종료하려면 반드시 호출
            }
        })

    }

    override fun onStart() {
        super.onStart()
        println("################## SecondActivity - onStart ##################")
    }

    override fun onResume() {
        super.onResume()
        println("################## SecondActivity - onResume ##################")
    }

    override fun onPause() {
        super.onPause()
        println("################## SecondActivity - onPause ##################")
    }

    override fun onStop() {
        super.onStop()
        println("################## SecondActivity - onStop ##################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("################## SecondActivity - onDestroy ##################")
    }

}