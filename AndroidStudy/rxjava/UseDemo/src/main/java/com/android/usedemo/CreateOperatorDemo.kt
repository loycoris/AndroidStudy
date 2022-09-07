package com.android.usedemo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.function.Consumer

class CreateOperatorDemo {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            println("========================")
            val demo = CreateOperatorDemo()
            demo.test1()
            println("========================")
        }
    }

    val observer = object : Observer<String> {
        override fun onSubscribe(d: Disposable?) {
            println("onSubscribe....")
        }

        override fun onNext(t: String?) {
            println("onNext...." + t)
        }

        override fun onError(e: Throwable?) {
            println("onError....")
        }

        override fun onComplete() {
            println("onComplete....")
        }
    }

    val consumer = object : Consumer<String> {
        override fun accept(p0: String) {
            TODO("Not yet implemented")
        }
    }

    fun test1() {
        Observable.create(ObservableOnSubscribe<String> {
            //事件产生的地方
            it.onNext("北京")
            it.onNext("生活")
            it.onNext("精彩")
            //onError和onComplete互斥
            it.onError(Throwable("手动丢出异常"))
            it.onComplete()
        }).subscribe(observer)

        Observable.create<Any> { emitter -> //事件产生的地方
            emitter!!.onNext("北京")
            emitter.onNext("生活")
            emitter.onNext("精彩")
            //onError和onComplete互斥
            emitter.onError(Throwable("手动丢出异常"))
            emitter.onComplete()
        }.subscribe({ o -> println("accept...$o") }
        ) { throwable -> println("accept...$throwable") }
    }
}
