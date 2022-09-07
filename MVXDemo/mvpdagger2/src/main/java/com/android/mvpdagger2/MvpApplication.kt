package com.android.mvpdagger2

import android.app.Application
import com.android.mvpdagger2.net.DaggerNetTaskComponent
import com.android.mvpdagger2.net.NetTaskComponent

import com.android.mvpdagger2.net.NetTaskModule


class MvpApplication : Application() {
    private var netTaskComponent: NetTaskComponent? = null
    override fun onCreate() {
        super.onCreate()
        netTaskComponent = DaggerNetTaskComponent.builder().netTaskModule(NetTaskModule()).build()
    }

    fun getTasksRepositoryComponent(): NetTaskComponent? {
        return netTaskComponent
    }
}