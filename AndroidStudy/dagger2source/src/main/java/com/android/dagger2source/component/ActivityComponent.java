package com.android.dagger2source.component;

import com.android.dagger2source.MainActivity;
import com.android.dagger2source.module.UserModule;
import com.android.dagger2source.module.WatchModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = UserModule.class)
public interface ActivityComponent {
    void inject(MainActivity mainActivity);
}
