package com.android.dagger.component;

import com.android.dagger.MainActivity;
import com.android.dagger.SecondActivity;
import com.android.dagger.annotation.ApplicationScope;
import com.android.dagger.module.EngineModule;
import com.android.dagger.module.GsonModule;

import dagger.Component;

//可以指定多个modules
@Component(modules = {GsonModule.class, EngineModule.class}, dependencies = SwordsmanComponent.class)
@ApplicationScope
public interface ActivityComponent {
    void inject(MainActivity mainActivity);

    void inject(SecondActivity secondActivity);
}
