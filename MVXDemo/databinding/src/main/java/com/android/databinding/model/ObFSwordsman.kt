package com.android.databinding.model

import androidx.databinding.ObservableField

class ObFSwordsman(_name: String, _level: String) {
    var name: ObservableField<String> = ObservableField(_name)
    var level: ObservableField<String> = ObservableField(_level)

//    constructor(_name: String, _level: String) : this(){
//        this.name.set(_name)
//        this.level.set(_level)
//    }

    /*init {
        this.name.set(_name)
        this.level.set(_level)
    }*/
}
