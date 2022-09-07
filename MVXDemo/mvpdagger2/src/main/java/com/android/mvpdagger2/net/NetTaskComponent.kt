package com.android.mvpdagger2.net

import com.android.mvpdagger2.model.IpInfo
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetTaskModule::class])
interface NetTaskComponent {
    fun getNetTask(): NetTask<String, IpInfo>
}