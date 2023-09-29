package com.example.week5_assignment

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student : Student)

    @Delete
    suspend fun deleteStudent(student : Student)

    @Query("SELECT * from student_table")
    fun getAllStudents(): LiveData<List<Student>>

    @Query("SELECT id, name FROM student_table WHERE id = :studentId")
    fun getStudentById(studentId: Int): LiveData<Student?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClassInfo(classInfo: ClassInfo)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEnrollment(enrollment: Enrollment)

    // 학생 ID에 따라 수강한 수업 목록 가져오기
    @Query("SELECT * FROM enrollment WHERE sid = :studentId")
    fun getEnrollmentsByStudentId(studentId: Int): LiveData<List<Enrollment>>

    // 수업 ID에 따라 수업 이름 가져오기
    @Query("SELECT name FROM class_table WHERE id = :classId")
    fun getClassNameById(classId: Int): LiveData<String>

    @Query("SELECT class_table.id, class_table.name FROM student_table " +
            "INNER JOIN enrollment ON student_table.id = enrollment.sid " +
            "INNER JOIN class_table ON enrollment.cid = class_table.id " +
            "WHERE student_table.id = :studentId")
    fun getClassesByStudentId(studentId: Int): LiveData<List<ClassInfo>>
}