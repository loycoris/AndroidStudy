package com.android.room2

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class StudentRespository(val context: Context) {
    private val studentDao = MyDatabase.getInstance(context).getStudentDao()

    fun insertStudent(vararg student: Student) {
        println("student name : ${student.get(0).name}")
        GlobalScope.launch(Dispatchers.IO) {
            studentDao.insertStudent(*student)
        }
    }

    fun deleteStudent(vararg student: Student) {
        GlobalScope.launch(Dispatchers.IO) {
            studentDao.deleteStudent(*student)
        }
    }

    fun deleteAllStudent() {
        GlobalScope.launch(Dispatchers.IO) {
            studentDao.deleteAllStudents()
        }
    }

    fun updateStudent(vararg student: Student) {
        GlobalScope.launch(Dispatchers.IO) {
            studentDao.updateStudent(*student)
        }
    }

    fun getAllStudentsLive(): LiveData<List<Student>> {
        return studentDao.getAllStudentsLive()
    }
}