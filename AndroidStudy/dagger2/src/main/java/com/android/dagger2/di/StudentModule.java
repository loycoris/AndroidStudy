package com.android.dagger2.di;

import dagger.Module;
import dagger.Provides;

@Module
public class StudentModule {
    @Provides
    public Student provideStudent() {
        return new Student();
    }
}
