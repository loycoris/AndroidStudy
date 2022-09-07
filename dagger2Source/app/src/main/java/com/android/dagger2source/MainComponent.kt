package com.android.dagger2source

import dagger.Component

@Component
interface MainComponent {
    fun inject(activity: MainActivity)
}