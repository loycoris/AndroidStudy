package com.android.lifecycle

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class ApplicationObserver : LifecycleObserver {
    val TAG = "lcy"

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() = Log.d(TAG,"Lifecycle.Event.ON_CREATE")

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = Log.d(TAG,"Lifecycle.Event.ON_START")

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = Log.d(TAG,"Lifecycle.Event.ON_RESUME")

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = Log.d(TAG,"Lifecycle.Event.ON_PAUSE")

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() = Log.d(TAG,"Lifecycle.Event.ON_STOP")

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestory() = Log.d(TAG,"Lifecycle.Event.ON_DESTROY")
}