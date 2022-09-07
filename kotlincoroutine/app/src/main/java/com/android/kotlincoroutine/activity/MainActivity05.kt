package com.android.kotlincoroutine.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.kotlincoroutine.R
import kotlin.coroutines.*

class MainActivity05 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //协程体
        val continuation = suspend {
            5
        }.createCoroutine(object : Continuation<Int> {
            override val context: CoroutineContext = EmptyCoroutineContext

            override fun resumeWith(result: Result<Int>) {
                println("Coroutine End : $result")
            }

        })

        continuation.resume(Unit)
    }
}