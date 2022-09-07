package com.android.room

import androidx.room.*

@Dao
public interface StudentDao {
    @Insert
    fun insertStudent(vararg student: Student)

    @Delete
    fun deleteStudent(vararg student: Student)

    @Update
    fun updateStudent(vararg student: Student)

    @Query("SELECT * FROM student")
    fun getAllStudent(): List<Student>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): List<Student>
}