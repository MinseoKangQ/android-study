package com.project.week7_assignment

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SecondActivity 에서의 값 받아오기
        val activityResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val msg = it.data?.getStringExtra("resultdata") ?: ""
            Snackbar.make(findViewById(R.id.button), msg, Snackbar.LENGTH_SHORT).show()
            findViewById<EditText>(R.id.editText).setText(msg)
        }

        // editText의 값 받아오기
        // 이 부분 잘 안됨
        // Intent로 넘기기
        val editText = findViewById<EditText>(R.id.editText)
        println("###################${editText.text}")


        // 버튼 누르면 SecondActivity 시작
        findViewById<Button>(R.id.button)?.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra("inputdata", "value")
            activityResult.launch(intent)
        }
    }
}