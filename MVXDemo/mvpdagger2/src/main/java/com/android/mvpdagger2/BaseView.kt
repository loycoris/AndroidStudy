package com.android.mvpdagger2


interface BaseView<T> {
    fun setPresenter(presenter: T)
}