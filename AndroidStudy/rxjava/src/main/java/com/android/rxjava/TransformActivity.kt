package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observables.GroupedObservable


class TransformActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_conversion)
        map()
        flatMap()
        concatMap()
        flatMapIterable()
        buffer()
        groupBy()
    }

    private fun map() {
        val Host = "http://blog.csdn.net/"
        Observable.just("itachi85").map { Host + it }
            .subscribe { Log.d(TAG, "map:$it") }
    }

    private fun flatMap() {
        val Host = "http://blog.csdn.net/"
        val mlist: MutableList<String> = ArrayList()
        mlist.add("itachi85")
        mlist.add("itachi86")
        mlist.add("itachi87")
        mlist.add("itachi88")
        Observable.fromIterable(mlist).flatMap { Observable.just(Host + it) }
            .cast(String::class.java)
            .subscribe { Log.d(TAG, "flatMap:" + it) }
    }

    private fun concatMap() {
        val Host = "http://blog.csdn.net/"
        val mlist: MutableList<String> = ArrayList()
        mlist.add("itachi85")
        mlist.add("itachi86")
        mlist.add("itachi87")
        mlist.add("itachi88")
        Observable.fromIterable(mlist).concatMap { Observable.just(Host + it) }
            .subscribe { Log.d(TAG, "concatMap:" + it) }
    }

    private fun flatMapIterable() {
        Observable.just(1, 2, 3).flatMapIterable {
            val mlist: MutableList<Int> = ArrayList()
            mlist.add(it + 1)
            mlist
        }.subscribe { Log.d(TAG, "flatMapIterable:$it") }
    }

    private fun buffer() {
        Observable.just(1, 2, 3, 4, 5, 6).buffer(3)
            .subscribe { Log.d(TAG, "buffer:" + it) }
    }

    private fun groupBy() {
        val s1 = Swordsman("韦一笑", "A")
        val s2 = Swordsman("张三丰", "SS")
        val s3 = Swordsman("周芷若", "S")
        val s4 = Swordsman("宋远桥", "S")
        val s5 = Swordsman("殷梨亭", "A")
        val s6 = Swordsman("张无忌", "SS")
        val s7 = Swordsman("鹤笔翁", "S")
        val s8 = Swordsman("宋青书", "A")
        val GroupedObservable: Observable<GroupedObservable<String, Swordsman>> =
            Observable.just(s1, s2, s3, s4, s5, s6, s7, s8)
                .groupBy { swordsman -> swordsman.level }
        Observable.concat(GroupedObservable).subscribe { swordsman ->
            Log.d(TAG, "groupBy:" + swordsman.name + "---" + swordsman.level)
        }
    }
}