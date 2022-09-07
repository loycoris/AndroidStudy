package com.android.databinding7

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel : ViewModel() {
    val aTeamScore: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }
    val bTeamScore: MutableLiveData<Int> by lazy { MutableLiveData<Int>(0) }

    /*val aTeamScore = MutableLiveData<Int>(0)
    val bTeamScore = MutableLiveData<Int>(0)*/

    var aLast = 0
    var bLast = 0

    fun aTeamAdd(i: Int) {
        saveLastScore()
        aTeamScore.value = aTeamScore.value?.plus(i)
    }

    fun bTeamAdd(i: Int) {
        saveLastScore()
        bTeamScore.value = bTeamScore.value?.plus(i)
    }

    fun undo(v: View) {
        aTeamScore.value = aLast
        bTeamScore.value = bLast
    }

    fun reset(v: View) {
        aTeamScore.value = 0
        bTeamScore.value = 0
    }

    fun saveLastScore() {
        aLast = aTeamScore.value!!
        bLast = bTeamScore.value!!
    }
}