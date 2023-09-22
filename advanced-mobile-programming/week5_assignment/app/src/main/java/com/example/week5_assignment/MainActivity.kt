package com.example.week5_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // db 만들기
        val db = MyDatabase.getDatabase(this)
        val dao = db.getMyDao()

        // 레코드 추가
        CoroutineScope(Dispatchers.IO).launch {
            dao.insertStudent(Student(1, "james"))
            dao.insertStudent(Student(2, "john"))
            dao.insertClassInfo(ClassInfo(1, "c-lang"))
        }

        // 등록된 학생들의 리스트 출력
        val allStudents = dao.getAllStudents()
        allStudents.observe(this) { // 옵저버 등록, LiveData 사용하므로 Coroutine 필요 X
            val str = StringBuilder().apply {
                for ((id, name) in it) {
                    append(id)
                    append("-")
                    append(name)
                    append("\n")
                }
            }.toString()

            val studentList = findViewById<TextView>(R.id.text_student_list)
            studentList.text = str
        }


        // 학생 조회 - 쿼리
        findViewById<Button>(R.id.query_student).setOnClickListener {
            val id = findViewById<EditText>(R.id.edit_student_id).text.toString()

            val studentLiveData = dao.getStudentById(id.toInt())
            val classesLiveData = dao.getClassesByStudentId(id.toInt())

            studentLiveData.observe(this) { student -> // 옵저버 등록, LiveData 사용하므로 Coroutine 필요 X
                classesLiveData.observe(this) { classes ->
                    val resultText = StringBuilder()

                    if (student != null) {
                        resultText.append("${student.id}-${student.name}")

                        if (classes.isNotEmpty()) {
                            resultText.append(":")
                            for (classWithIdAndName in classes) {
                                resultText.append("${classWithIdAndName.id}(${classWithIdAndName.name}),")
                            }
                        }
                    } else { }

                    val queryResult = findViewById<TextView>(R.id.text_query_student)
                    queryResult.text = resultText
                }
            }
        }

        // 학생 추가
        findViewById<Button>(R.id.add_student).setOnClickListener {
            val id = findViewById<EditText>(R.id.edit_student_id).text.toString()
            val name = findViewById<EditText>(R.id.edit_student_name).text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                dao.insertStudent(Student(id.toInt(), name))
            }
        }

        // 학생 삭제
        findViewById<Button>(R.id.del_student).setOnClickListener {
            val id = findViewById<EditText>(R.id.edit_student_id).text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                dao.deleteStudent(Student(id.toInt(), ""))
            }
        }

        findViewById<Button>(R.id.enroll).setOnClickListener {
            val id = findViewById<EditText>(R.id.edit_student_id).text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                dao.insertEnrollment(Enrollment(id.toInt(), 1))
            }
        }

    }
}