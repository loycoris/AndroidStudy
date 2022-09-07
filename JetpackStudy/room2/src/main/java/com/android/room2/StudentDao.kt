package com.android.room2

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
public interface StudentDao {
    @Insert
    fun insertStudent(vararg student: Student)

    @Delete
    fun deleteStudent(vararg student: Student)

    @Update
    fun updateStudent(vararg student: Student)

    @Query("DELETE FROM student")
    fun deleteAllStudents()

    @Query("SELECT * FROM student")
    fun getAllStudentsLive(): LiveData<List<Student>>

    @Query("SELECT * FROM student WHERE id = :id")
    fun getStudentById(id: Int): List<Student>
}