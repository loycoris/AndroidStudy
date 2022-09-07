package com.android.livedata

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import java.util.*
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    //    lateinit var mainViewModel: MainViewModel
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var tvNum: TextView
    private lateinit var tvName: TextView
    private lateinit var btnChange: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvNum = findViewById(R.id.textview)
        tvName = findViewById(R.id.textView2)
        btnChange = findViewById(R.id.button)

//        mainViewModel =
//            ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(
//                MainViewModel::class.java
//            )
//        tvNum.text = mainViewModel.currentSecond.value.toString()

        mainViewModel.currentSecond.observe(this) { tvNum.text = it.toString() }
        startTimer()

        btnChange.setOnClickListener {
            var changeVal = (0..100).random()
//            mainViewModel.name.value = "study jetpack: $changeVal"
            mainViewModel.update("study jetpack: $changeVal")
        }
        mainViewModel.name.observe(this) {
            tvName.text = it.toString()
        }
    }

    private fun startTimer() {
        Timer().schedule(object : TimerTask() {
            override fun run() {
                //非UI线程用postValue
                //UI线程用setValue
                mainViewModel.currentSecond.postValue(mainViewModel.currentSecond.value?.plus(1))
            }

        }, 1000, 1000)
    }
}