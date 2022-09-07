package com.android.mvpdagger2

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {
    private var mContext: Context? = null
    fun ApplicationModule(context: Context?) {
        mContext = context
    }

    @Provides
    fun provideContext(): Context? {
        return mContext
    }
}