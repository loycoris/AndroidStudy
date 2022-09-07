package com.android.databinding.model

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import com.android.databinding.BR


class ObSwordsman(_name: String, _level: String) : BaseObservable() {
    @get:Bindable
    var name: String = _name
        set(_name) {
            field = _name
            notifyPropertyChanged(BR.name)
        }

    @get:Bindable
    var level: String = _level
        set(_level) {
            field = _level
            notifyPropertyChanged(BR.level)
        }
}