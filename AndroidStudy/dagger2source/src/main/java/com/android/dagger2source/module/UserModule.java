package com.android.dagger2source.module;

import com.android.dagger2source.model.User;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Singleton
    @Provides
    public User provideUser(){
        return new User();
    }
}
