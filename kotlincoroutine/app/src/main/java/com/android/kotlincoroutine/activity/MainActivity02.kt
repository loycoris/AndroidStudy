package com.android.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.*

class MainActivity02 : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    @DelicateCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            //协程
            GlobalScope.launch(Dispatchers.Main) { //父协程
                val user = withContext(Dispatchers.IO) { //子协程
                    userServiceApi.getUser("123")
                }
                nameTextView.text = "success:${user.success}"
            }
        }
    }
}