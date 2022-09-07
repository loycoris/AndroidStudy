package com.android.livedata2

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val progress = MutableLiveData<Int>(0)

//    init {
//        progress.value = 0
//    }
}