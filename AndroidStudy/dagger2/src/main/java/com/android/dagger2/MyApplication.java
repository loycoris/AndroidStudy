package com.android.dagger2;

import android.app.Application;

import com.android.dagger2.di.ApplicationComponent;
import com.android.dagger2.di.DaggerApplicationComponent;
import com.android.dagger2.di.NetworkModule;

public class MyApplication extends Application {
    static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = DaggerApplicationComponent.builder().networkModule(new NetworkModule(this)).build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
