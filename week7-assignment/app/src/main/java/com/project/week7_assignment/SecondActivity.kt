package com.project.week7_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.lifecycle.ViewModelProvider

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // MainActivity 에서 데이터 받아오기
        // Intent로 전달 받은 숫자를 표시
        // 이 부분 수정해야 함
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
                resultIntent.putExtra("resultdata", "${findViewById<TextView>(R.id.textView).text}")
                setResult(RESULT_OK, resultIntent)
                finish()
            }

        })

        // 뷰 모델 객체 가져오기
        val viewModel = ViewModelProvider(this, MyViewModel2Factory(10))[MyViewModel::class.java]
        viewModel.countLiveData.observe(this){
            findViewById<TextView>(R.id.textView).text = "${it}"
        }

        // 버튼 누르면 증가
        findViewById<Button>(R.id.buttonInc)?.setOnClickListener {
            viewModel.increaseCount()
        }

        // 버튼 누르면 감소
        findViewById<Button>(R.id.buttonDec)?.setOnClickListener {
            viewModel.decreaseCount()
        }

    }
}
