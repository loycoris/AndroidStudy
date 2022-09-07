package com.android.dagger;

import android.app.Application;
import android.content.Context;

import com.android.dagger.component.ActivityComponent;
import com.android.dagger.component.DaggerActivityComponent;
import com.android.dagger.component.DaggerSwordsmanComponent;

public class App extends Application {
    ActivityComponent activityComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        activityComponent = DaggerActivityComponent.builder().swordsmanComponent(DaggerSwordsmanComponent.builder().build()).build();
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }

    ActivityComponent getActivityComponent() {
        return activityComponent;
    }
}
