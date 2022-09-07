package com.android.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val currentSecond = MutableLiveData<Int>(0)

    val name: MutableLiveData<String> by lazy { MutableLiveData<String>() }

    fun update(_name: String) {
        name.value = _name
    }

    /*init {
        currentSecond.value = 0
    }*/
}