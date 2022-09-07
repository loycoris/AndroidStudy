package com.android.mvpdagger2

interface LoadTasksCallBack<T> {
    fun onSuccess(t: T)
    fun onStart()
    fun onFailed()
    fun onFinish()
}