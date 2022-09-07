package com.android.mvpsimple

import android.app.Application
import cn.finalteam.okhttpfinal.OkHttpFinal
import cn.finalteam.okhttpfinal.OkHttpFinalConfiguration

class MvpApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val builder: OkHttpFinalConfiguration.Builder = OkHttpFinalConfiguration.Builder();
        OkHttpFinal.getInstance().init(builder.build())
    }
}