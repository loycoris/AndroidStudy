package com.android.kotlincoroutine.activity

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.TextView
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.api.User
import com.android.kotlincoroutine.api.UserServiceApi
import com.android.kotlincoroutine.api.userServiceApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call

class MainActivity01 : AppCompatActivity() {
    @SuppressLint("StaticFieldLeak")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nameTextView = findViewById<TextView>(R.id.nameTextView)
        nameTextView.text = "Jack"

        val submitButton = findViewById<Button>(R.id.submitButton).also {
            it.setOnClickListener {
                //异步任务
                object : AsyncTask<Void, Void, User>() {
                    override fun doInBackground(vararg p0: Void?): User? {
                        return userServiceApi.loadUser("123").execute().body()
                    }

                    override fun onPostExecute(user: User?) {
                        nameTextView.text = "success:${user?.success}"
                    }

                }.execute()
            }
        }
    }
}