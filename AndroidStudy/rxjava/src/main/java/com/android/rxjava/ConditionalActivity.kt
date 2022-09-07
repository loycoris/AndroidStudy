package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import java.util.concurrent.TimeUnit
import java.util.function.Consumer

/**
 * 条件和布尔操作符
 */
class ConditionalActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conditional)
        all()
        contains()
        isEmpty()
        amb()
        defaultIfEmpty()
    }

    private fun defaultIfEmpty() {
        Observable.create(ObservableOnSubscribe<Int> { it.onComplete() })
            .defaultIfEmpty(3)
            .subscribe { Log.d(TAG, "defaultIfEmpty:$it") }
    }

    private fun amb() {
        Observable.ambArray(
            Observable.just(1, 2, 3).delay(2, TimeUnit.SECONDS),
            Observable.just(4, 5, 6)
        )
            .subscribe { it -> Log.d(TAG, "amb:$it") }
    }

    private fun isEmpty() {
        Observable.just(1, 2, 3)
            .isEmpty
            .subscribe { it -> Log.d(TAG, "isEmpty:$it") }
    }

    private fun contains() {
        Observable.just(1, 2, 3)
            .contains(1)
            .subscribe { it -> Log.d(TAG, "contains:$it") }
    }

    private fun all() {
        Observable.just(1, 2, 3)
            .all { integer ->
                Log.d(TAG, "call:" + integer)
                integer < 2
            }
            .subscribe { aBoolean -> Log.d(TAG, "accept--:$aBoolean") }
    }
}