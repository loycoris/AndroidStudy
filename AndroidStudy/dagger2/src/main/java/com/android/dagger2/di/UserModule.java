package com.android.dagger2.di;

import dagger.Module;
import dagger.Provides;

@Module
public class UserModule {
    @Provides
    @UserScope
    public User provideUser() {
        return new User();
    }
}
