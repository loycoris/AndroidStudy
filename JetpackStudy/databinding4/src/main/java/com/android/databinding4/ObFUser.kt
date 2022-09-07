package com.android.databinding4

import android.util.Log
import androidx.databinding.ObservableField

class ObFUser {
    val userObF: ObservableField<User> = ObservableField()

    init {
        val user = User("jack")
        userObF.set(user)
    }

    var name = userObF.get()?.userName
        set(value) {
            field = value
            Log.d("lcy","set userObF username: $value")
        }
}