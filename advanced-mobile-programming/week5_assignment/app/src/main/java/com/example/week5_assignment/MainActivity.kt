package com.example.week5_assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.room.util.query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.w3c.dom.Text

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
            dao.insertStudent(Student(2, "test"))
            dao.insertStudent(Student(3, "tom"))
        }

        // 등록된 학생들의 리스트 출력
        val allStudents = dao.getAllStudents()
        allStudents.observe(this) { // 옵저버 등록, LiveData 사용하므로 Coroutine 필요 X
            val str = StringBuilder().apply {
                for((id, name) in it) {
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
            val student = dao.getStudentById(id.toInt())

            // 조회 결과를 출력
            student.observe(this) { stu -> // 옵저버 등록, LiveData 사용하므로 Coroutine 필요 X
                var result : String? = null

                if (stu != null) { result = "${stu.id}-${stu.name}:" }
                else { result = "Not Found" }

                val queryResult = findViewById<TextView>(R.id.text_query_student)
                queryResult.text = result
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

    }
}