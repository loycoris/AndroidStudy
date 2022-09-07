package com.android.room

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.room.databinding.ActivityMainBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private var studentDao: StudentDao? = null
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: List<Student>
    private lateinit var adapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list = ArrayList()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = RecycleViewAdapter(list)
        binding.recycler.adapter = adapter

        val database = MyDatabase.getInstance(this)
        studentDao = database.getStudentDao()
    }

    fun mInsert(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val stu1 = Student("Jack", 20)
            val stu2 = Student("Rose", 18)
            studentDao?.insertStudent(stu1, stu2)
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "插入完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mDelete(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            val stu1 = Student(2)
            studentDao?.deleteStudent(stu1)
            withContext(Dispatchers.Main) {
                Toast.makeText(applicationContext, "删除完成", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun mUpdate(view: View) {
        val stu1 = Student(3, "Jason", 21)
        val stu2 = Student(5, "Tim", 18)
        GlobalScope.launch(Dispatchers.IO) {
            studentDao?.updateStudent(stu1, stu2)
        }
    }

    fun mQuery(view: View) {
        GlobalScope.launch(Dispatchers.IO) {
            list = studentDao?.getAllStudent()!!
            println("list size = ${list.size}")
            adapter.list = list

            withContext(Dispatchers.Main) {
                adapter.notifyDataSetChanged()
                Toast.makeText(applicationContext, "查询完成", Toast.LENGTH_SHORT).show()
            }
        }
    }
}