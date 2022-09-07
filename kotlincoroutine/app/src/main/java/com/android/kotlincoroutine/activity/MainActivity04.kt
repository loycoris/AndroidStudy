package com.android.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.api.UserServiceApi
import com.android.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity04 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                //协程
                /*GlobalScope.launch(Dispatchers.Main) { //父协程
                    //挂起
                    delay(12000)
                    Log.d("jason", "${Thread.currentThread().name}:after delay")
                }*/
                //阻塞
                Thread.sleep(12000)
                Log.d("jason", "${Thread.currentThread().name}:after sleep")
            }
        }
    }
}