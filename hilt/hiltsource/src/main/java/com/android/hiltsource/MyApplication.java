package com.android.hiltsource;

import android.app.Application;
import android.util.Log;

import dagger.hilt.android.HiltAndroidApp;

@HiltAndroidApp
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG", "oncreate: " + this.getClass().getSuperclass().getSimpleName());
    }
}
