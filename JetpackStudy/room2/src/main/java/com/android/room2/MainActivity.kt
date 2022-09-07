package com.android.room2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels

import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.room2.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var list: List<Student>
    private lateinit var adapter: RecycleViewAdapter
    private val viewModel: StudentViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        list = ArrayList()
        binding.recycler.layoutManager = LinearLayoutManager(this)
        adapter = RecycleViewAdapter(list)
        binding.recycler.adapter = adapter

        val stu1 = Student("Tom", 22)
        val stu2 = Student("Bob", 35)
        binding.student = stu1
        binding.deletestudent = Student(2)
        binding.updatestudent = Student(3, "Lily", 44)

        viewModel.getAllStudentsLive().observe(this, Observer {
            adapter.list = it
            println("student name : ${it.size}")
            adapter.notifyDataSetChanged()
        })
    }
}