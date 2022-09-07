package com.android.mvpdagger2.ipinfo

import com.android.mvpdagger2.ipinfo.IpInfoContract
import dagger.Module
import dagger.Provides

@Module
class IpInfoModule {
    private var mView: IpInfoContract.View? = null
    fun IpInfoModule(mView: IpInfoContract.View?) {
        this.mView = mView
    }

    @Provides
    fun provideIpInfoContract(): IpInfoContract.View? {
        return mView
    }
}