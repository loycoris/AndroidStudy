package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable

/**
 *转换操作符
 */
class ConversionActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion2)
        toList()
        toMap()
        toSortedList()
    }

    private fun toSortedList() {
        Observable.just(3, 1, 2)
            .toSortedList()
            .subscribe { it -> Log.i(TAG, "toSortedList:" + it) }

    }

    private fun toMap() {
        val s1 = Swordsman("韦一笑", "A")
        val s2 = Swordsman("张三丰", "SS")
        val s3 = Swordsman("周芷若", "S")
        Observable.just(s1, s2, s3)
            .toMap { it.level }
            .subscribe { it -> Log.i(TAG, "toMap:" + it.get("SS")?.name) }
    }

    private fun toList() {
        Observable.just(1, 2, 3)
            .toList()
            .subscribe { it ->
                for (integer in it) {
                    Log.i(TAG, "toList:$integer")
                }
            }
    }
}