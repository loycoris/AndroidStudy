package com.android.hilt;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ActivityContext;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.android.scopes.ActivityScoped;

@InstallIn(ActivityComponent.class)
@Module
public class AppModule {
    @ActivityScoped
//    @Singleton
    @Provides
    public User provideUser() {
        return new User();
    }

    @Provides
    public ViewModel provideViewModel(User user, Application application, Activity activity,@ActivityContext Context contex) {
        return new ViewModel(user, application, activity, contex);
    }

    @Provides
    public MainViewModel provideMainViewModel(User user, Application application, Activity activity,@ActivityContext Context contex) {
        return new MainViewModel(user, application, activity, contex);
    }
}
