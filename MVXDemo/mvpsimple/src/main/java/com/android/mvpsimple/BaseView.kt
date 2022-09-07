package com.android.mvpsimple


interface BaseView<T> {
    fun setPresenter(presenter: T)
}