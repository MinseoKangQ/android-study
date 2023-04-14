package com.project.week7

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
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