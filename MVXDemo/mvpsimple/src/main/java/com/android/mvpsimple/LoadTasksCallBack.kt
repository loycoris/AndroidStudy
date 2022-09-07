package com.android.mvpsimple

interface LoadTasksCallBack<T> {
    fun onSuccess(t: T)
    fun onStart()
    fun onFailed()
    fun onFinish()
}