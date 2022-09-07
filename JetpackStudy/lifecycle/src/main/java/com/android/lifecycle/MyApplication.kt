package com.android.lifecycle

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //针对整个应用程序的监听，与Activity数量无关
        //Lifecycle.Event.ON_CREATE只会被调用一次，Lifecycle.Event.ON_DESTROY永远不会调用
        ProcessLifecycleOwner.get().getLifecycle().addObserver(ApplicationObserver())
    }
}