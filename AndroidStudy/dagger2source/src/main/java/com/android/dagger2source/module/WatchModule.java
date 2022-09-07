package com.android.dagger2source.module;

import com.android.dagger2source.model.Watch;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class WatchModule {
    @Provides
    public Watch provideWatch(){
        return new Watch();
    }
}
