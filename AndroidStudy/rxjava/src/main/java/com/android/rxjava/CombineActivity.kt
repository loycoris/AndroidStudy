package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class CombineActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_combine)
        startWith()
        merge()
        concat()
        zip()
        combineLastest()
    }

    private fun combineLastest() {
        val obs1 = Observable.just(1, 2, 3)
        val obs2 = Observable.just("a", "b", "c")
        Observable.combineLatest(obs1, obs2, { t1, t2 -> t1.toString() + t2 })
            .subscribe { Log.d(TAG, "combineLastest:$it") }
    }

    private fun zip() {
        val obs1 = Observable.just(1, 2, 3)
        val obs2 = Observable.just("a", "b", "c")
        Observable.zip(obs1, obs2,
            { t1, t2 -> t1.toString() + t2 }).subscribe { Log.d(TAG, "zip:$it") }
    }

    private fun concat() {
        val obs1 = Observable.just(1, 2, 3).subscribeOn(Schedulers.io())
        val obs2 = Observable.just(4, 5, 6)
        Observable.concat(obs1, obs2).subscribe { Log.d(TAG, "concat:" + it) }
    }

    private fun merge() {
        val obs1 = Observable.just(1, 2, 3).subscribeOn(Schedulers.io())
        val obs2 = Observable.just(4, 5, 6)
        Observable.merge(obs1, obs2).subscribe { Log.d(TAG, "merge:" + it) }

    }

    private fun startWith() {
        Observable.just("hello", "world")
            .startWith(Observable.just("rxjava"))
            .subscribe { Log.d(TAG, "startWith:" + it) }
    }
}