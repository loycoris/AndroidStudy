package com.android.mvpdagger2.net

import com.android.mvpdagger2.model.IpInfo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class NetTaskModule {
    @Singleton
    @Provides
    fun provideIpInfoTask(): NetTask<String, IpInfo> {
        return IpInfoTask()
    }
}