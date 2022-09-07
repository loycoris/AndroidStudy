package com.android.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider

class MainActivity : AppCompatActivity() {
    private lateinit var textview: TextView

    //    private lateinit var viewModel: MyViewModel
    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textview = findViewById(R.id.textview)

//        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(application)).get(MyViewModel::class.java)

        textview.text = viewModel.number.toString()
    }

    fun plusNumber(view: View) {
        textview.text = ((++viewModel.number)).toString()
        /*var num = viewModel.number
        num++
        textview.text = num.toString()
        viewModel.number = num*/
    }
}