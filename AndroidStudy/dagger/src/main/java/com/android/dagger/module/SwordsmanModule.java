package com.android.dagger.module;

import com.android.dagger.model.Swordsman;

import dagger.Module;
import dagger.Provides;

@Module
public class SwordsmanModule {
    @Provides
    public Swordsman provideSwordsman() {
        return new Swordsman();
    }
}
