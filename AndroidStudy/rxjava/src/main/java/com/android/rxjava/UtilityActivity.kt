package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers


/**
 * 辅助/错误操作符
 */
class UtilityActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_utility)
        delay()
        doOnNext()
        subscribeOn()
        timeout()
    }

    private fun timeout() {
        val obs: Observable<Int> = Observable.create(ObservableOnSubscribe<Int> {
            for (i in 0..3) {
                try {
                    Thread.sleep((i * 100).toLong())
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
                it.onNext(i)
            }
            it.onComplete()

        }).timeout(200, TimeUnit.MILLISECONDS, Observable.just(10, 11))
        obs.subscribe { Log.d(TAG, "timeout:$it") }
    }

    private fun subscribeOn() {
        val obs: Observable<Int> = Observable.create {
            Log.d(TAG, "Observable:" + Thread.currentThread().name)
            it.onNext(1)
            it.onComplete()
        }
        obs.subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread())
            .subscribe { Log.d(TAG, "Observer:" + Thread.currentThread().name) }
    }

    private fun doOnNext() {
        Observable.just(1, 2)
            .doOnNext { Log.d(TAG, "call:" + it) }
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "onSubscribe...")
                }

                override fun onNext(t: Int?) {
                    Log.d(TAG, "onNext:" + t)
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, "Error:" + e?.message)
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }
            })
    }

    private fun delay() {
        Observable.create(ObservableOnSubscribe<Long> { emitter ->
            val currentTime = System.currentTimeMillis() / 1000
            emitter.onNext(currentTime)
        }).delay(2, TimeUnit.SECONDS)
            .subscribe { Log.d(TAG, "delay:" + (System.currentTimeMillis() / 1000 - it)) }
    }
}