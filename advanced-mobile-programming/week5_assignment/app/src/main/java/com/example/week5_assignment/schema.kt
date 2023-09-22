package com.example.week5_assignment

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "student_table")
data class Student(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(tableName = "class_table")
data class ClassInfo(
    @PrimaryKey val id: Int,
    val name: String
)

@Entity(tableName = "enrollment", primaryKeys = ["sid", "cid"],
    foreignKeys = [
        ForeignKey(entity = Student::class, parentColumns = ["id"], childColumns = ["sid"], onDelete = ForeignKey.CASCADE),
        ForeignKey(entity = ClassInfo::class, parentColumns = ["id"], childColumns = ["cid"])
    ]
)
data class Enrollment (
    val sid: Int,
    val cid: Int,
)