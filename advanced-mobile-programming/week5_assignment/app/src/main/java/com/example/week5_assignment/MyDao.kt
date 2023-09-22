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


}