package com.android.databinding4

import android.util.Log
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.android.databinding4.BR

class UserViewmodel(user: User) : BaseObservable() {
    @get:Bindable
    var name = user.userName
        set(value) {
            field = value
            notifyPropertyChanged(BR.userViewModel)
            Log.d("lcy","set username: $value")
        }

}