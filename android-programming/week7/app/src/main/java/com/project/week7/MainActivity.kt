package com.project.week7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        println("################## MainActivity - onCreate ##################")
        setContentView(R.layout.activity_main)

        // SecondActivity 에서의 값을 받아오기
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val msg = it.data?.getStringExtra("resultdata") ?: ""
            Snackbar.make(findViewById(R.id.button), msg, Snackbar.LENGTH_SHORT).show()
        }

        // 버튼을 누르면 secondActivity 시작
        findViewById<Button>(R.id.button)?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)// secondActivity 실행하려면 intent 만들어야 함
            // key : userdata, value : hello 가 SecondActivity 전달됨
            intent.putExtra("userdata", "hello")
            // startActivity(intent) // intent 지정
            activityResult.launch(intent)
        }

        // 뷰 모엘 객체 가져오기
        val viewModel = ViewModelProvider(this)[MyViewModel::class.java]
        viewModel.countLiveData.observe(this) { // it은 변경된 countLiveData를 말함
            findViewById<TextView>(R.id.textView3).text = "${it}"
        }

        // 버튼 누르면 증가
        findViewById<Button>(R.id.button2)?.setOnClickListener {
            viewModel.increaseCount()
        }

    }

    // 라이프 사이클 콜백 추가
    override fun onStart() {
        super.onStart()
        println("################## MainActivity - onStart ##################")
    }

    override fun onResume() {
        super.onResume()
        println("################## MainActivity - onResume ##################")
    }

    override fun onPause() {
        super.onPause()
        println("################## MainActivity - onPause ##################")
    }

    override fun onStop() {
        super.onStop()
        println("################## MainActivity - onStop ##################")
    }

    override fun onDestroy() {
        super.onDestroy()
        println("################## MainActivity - onDestroy ##################")
    }
}