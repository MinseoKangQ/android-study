package com.android.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

// https://firebase.google.com/docs/auth/android/password-auth?hl=ko 참고
// Activity 전환 : Intent 사용
// Intent 에 데이터 넣기 : putExtra 사용
// SIGN UP : 회원가입
// SIGN IN : 로그인
// SIGN OUT : 로그아웃
// Project 보기, 앱 하위의 .gitignore : google-service.json 추가

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Remote Config
        val remoteConfig = Firebase.remoteConfig
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 1 // For test purpose only, 3600 seconds for production
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
        remoteConfig.setDefaultsAsync(R.xml.remote_config)

        val textView = findViewById<TextView>(R.id.textView)
        remoteConfig.fetchAndActivate()
            .addOnCompleteListener(this) { // it: task
                val test = remoteConfig.getBoolean("test")
                textView.text = "${test}"
            }

        // 회원가입
        findViewById<Button>(R.id.signUpBtn)?.setOnClickListener {
            val userEmail = findViewById<EditText>(R.id.username)?.text.toString()
            val password = findViewById<EditText>(R.id.password)?.text.toString()
            doSignUp(userEmail, password)
        }

        // 로그인
        findViewById<Button>(R.id.signInBtn)?.setOnClickListener {
            val userEmail = findViewById<EditText>(R.id.username)?.text.toString()
            val password = findViewById<EditText>(R.id.password)?.text.toString()
            doLogin(userEmail, password)
        }
    }

    // 회원가입
    private fun doSignUp(userEmail: String, password: String) {
        Firebase.auth.createUserWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener(this) {task ->
                if (task.isSuccessful) {
                    Log.d("LoginActivity", "createUserWithEmail:success")
                    val uId = Firebase.auth.currentUser?.uid
                    val mainIntent = Intent(this, MainActivity::class.java)
                    mainIntent.putExtra("uId", uId)
                    startActivity(mainIntent)
                } else {
                    Log.w("LoginActivity", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    // 로그인
    private fun doLogin(userEmail: String, password: String) {
        Firebase.auth.signInWithEmailAndPassword(userEmail, password)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    val uId = Firebase.auth.currentUser?.uid
                    val mainIntent = Intent(this, MainActivity::class.java)
                    mainIntent.putExtra("uId", uId)
                    startActivity(mainIntent)
                } else {
                    Log.w("LoginActivity", "signInWithEmail", it.exception)
                    Toast.makeText(this, "Authentications failed", Toast.LENGTH_SHORT).show()
                }
            }
    }

}
