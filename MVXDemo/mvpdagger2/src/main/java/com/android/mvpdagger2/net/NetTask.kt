package com.android.mvpdagger2.net

import com.android.mvpdagger2.LoadTasksCallBack
import io.reactivex.rxjava3.disposables.Disposable


interface NetTask<T, P> {
    fun execute(data: T, callBack: LoadTasksCallBack<P>): Disposable
}