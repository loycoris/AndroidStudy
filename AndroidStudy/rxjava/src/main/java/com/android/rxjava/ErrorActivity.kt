package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers


class ErrorActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_error)
        onErrorReturn()
        retry()
    }

    private fun retry() {
        Observable.create(ObservableOnSubscribe<Int> {
            for (i in 0..4) {
                if (i == 1) {
                    it.onError(Throwable("Throwable"))
                } else {
                    it.onNext(i)
                }
            }
            it.onComplete()
        }).subscribeOn(Schedulers.newThread())
            .retry(2).subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError:" + e.message)
                }

                override fun onNext(integer: Int) {
                    Log.d(TAG, "onNext:$integer")
                }
            })
    }

    private fun onErrorReturn() {
        Observable.create(ObservableOnSubscribe<Int> {
            for (i in 0..4) {
                if (i > 2) {
                    it.onError(Throwable("Throwable"))
                }
                it.onNext(i)
            }
            it.onComplete()
        }).onErrorReturn { 6 }
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onError:" + e.message)
                }

                override fun onNext(integer: Int) {
                    Log.d(TAG, "onNext:$integer")
                }

                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "onSubscribe")
                }
            })
    }
}