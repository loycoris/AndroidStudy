package com.android.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toolbar
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.api.UserServiceApi
import com.android.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.*
import kotlin.coroutines.*

class MainActivity06 : AppCompatActivity(), CoroutineScope by MainScope() {
//    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                /*mainScope.launch {
                    val user = userServiceApi.getUser("123")
                    nameTextView.text = "sucess:${user.success}"
                    /*try {
                        delay(10000)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }*/
                }*/
                launch {
                    val user = userServiceApi.getUser("123")
                    nameTextView.text = "sucess:${user.success}"

                    try {
                        delay(10000)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        mainScope.cancel()
        cancel()
    }
}