package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit


class FilterActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        filter();
        elementAt();
        distinct();
        skip();
        take();
        ignoreElements();
        throttleFirst();
        throttleWithTimeOut()
    }

    private fun throttleWithTimeOut() {
        Observable.create(ObservableOnSubscribe<Integer> {
            for (i in 0..9) {
                it.onNext(i as @NonNull Integer)
                var sleep = 100
                if (i % 3 == 0) {
                    sleep = 300
                }
                try {
                    Thread.sleep(sleep.toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            it.onComplete()
        }).throttleWithTimeout(200, TimeUnit.MILLISECONDS)
            .subscribe { Log.d(TAG, "throttleWithTimeOut:" + it) }
    }

    private fun throttleFirst() {
        Observable.create(ObservableOnSubscribe<Integer> {
            for (i in 0..9) {
                it.onNext(i as @NonNull Integer)
                try {
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
            it.onComplete()
        }).throttleFirst(200, TimeUnit.MILLISECONDS)
            .subscribe { Log.d(TAG, "throttleFirst:" + it) }
    }

    private fun ignoreElements() {
        Observable.just(1, 2, 3, 4).ignoreElements()
            .subscribe(object : CompletableObserver {
                override fun onSubscribe(d: Disposable?) {
                    Log.i(TAG, "onSubscribe")
                }

                override fun onError(e: Throwable?) {
                    Log.i(TAG, "onError")
                }

                override fun onComplete() {
                    Log.i(TAG, "onCompleted")
                }
            })
    }

    private fun take() {
        Observable.just(1, 2, 3, 4, 5, 6).take(2)
            .subscribe { Log.d(TAG, "take:" + it) }

    }

    private fun skip() {
        Observable.just(1, 2, 3, 4, 5, 6).skip(2)
            .subscribe { Log.d(TAG, "skip:" + it) }

    }

    private fun distinct() {
        Observable.just(1, 2, 2, 3, 4, 1).distinct()
            .subscribe { Log.d(TAG, "distinct:" + it) }
    }

    private fun elementAt() {
        Observable.just(1, 2, 3, 4).elementAt(2)
            .subscribe { Log.d(TAG, "elementAt:" + it) }
    }

    private fun filter() {
        Observable.just(1, 2, 3, 4).filter { it > 2 }
            .subscribe { Log.d(TAG, "filter:" + it) }
    }
}