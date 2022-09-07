package com.android.lifecycle2

import android.util.Log
import androidx.lifecycle.*

class MyObserver(val lifecycle: Lifecycle) : LifecycleEventObserver {
    val TAG = "lcy"

    override fun onStateChanged(source: LifecycleOwner, event: Lifecycle.Event) {

        Log.d(TAG, "currentState:${lifecycle.currentState.name}")
        Log.d(TAG, "event:$event")
    }
}