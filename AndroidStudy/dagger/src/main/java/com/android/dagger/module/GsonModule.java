package com.android.dagger.module;

import com.android.dagger.annotation.ApplicationScope;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class GsonModule {
    @Provides
    @ApplicationScope
    public Gson provideGson() {
        return new Gson();
    }
}
