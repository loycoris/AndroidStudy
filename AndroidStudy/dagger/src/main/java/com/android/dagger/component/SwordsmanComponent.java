package com.android.dagger.component;

import com.android.dagger.MainActivity;
import com.android.dagger.annotation.ApplicationScope;
import com.android.dagger.model.Swordsman;
import com.android.dagger.module.SwordsmanModule;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = SwordsmanModule.class)
public interface SwordsmanComponent {
    Swordsman getSwordsman();
}
