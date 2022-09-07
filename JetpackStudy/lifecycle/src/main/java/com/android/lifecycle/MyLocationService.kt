package com.android.lifecycle

import android.util.Log
import androidx.lifecycle.LifecycleService

class MyLocationService : LifecycleService() {
    init {
        Log.d("lcy", "MyLocationService")
        val observer = MyLocationObserver(this)
        lifecycle.addObserver(observer)
    }
}