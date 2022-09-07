package com.android.kotlincoroutine.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.*

class MainActivity03 : AppCompatActivity() {
    var nameTextView: TextView? = null

    @DelicateCoroutinesApi
    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView?.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            //协程
            GlobalScope.launch(Dispatchers.Main) { //父协程
                getUser()
            }
        }
    }

    //挂起函数
    private suspend fun getUser() {
        val user = get()
        show(user)
    }

    private suspend fun get(): User = withContext(Dispatchers.IO) { //子协程
        userServiceApi.getUser("123")
    }

    private fun show(user: User) {
        nameTextView?.text = "success:${user?.success}"
    }
}
