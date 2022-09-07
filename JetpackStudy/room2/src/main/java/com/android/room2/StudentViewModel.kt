package com.android.room2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class StudentViewModel(application: Application) : AndroidViewModel(application) {
    val respository = StudentRespository(application)

    fun insertStudent( student: Student) =
        println("student name : ${student.name}")
//        respository.insertStudent(student)

    fun deleteStudent(vararg student: Student) = respository.deleteStudent(*student)

    fun deleteAllStudent() = respository.deleteAllStudent()

    fun updateStudent(vararg student: Student) = respository.updateStudent(*student)

    fun getAllStudentsLive(): LiveData<List<Student>> = respository.getAllStudentsLive()
}