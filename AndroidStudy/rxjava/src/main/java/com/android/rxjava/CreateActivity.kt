package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 * 创建操作符
 */
class CreateActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)
//        interval()
        range()
        repeat()
    }

    private fun repeat() {
        Observable.range(0, 3)
            .repeat(2)
            .subscribe { it -> Log.d(TAG, "repeat:" + it.toInt()); }
    }

    private fun range() {
        Observable.range(0, 5)
            .subscribe { Log.d(TAG, "range:$it") }
    }

    private fun interval() {
        Observable.interval(3, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "interval:" + it.toInt()) }
    }
}