package com.android.dagger.module;

import com.android.dagger.annotation.Diesel;
import com.android.dagger.annotation.Gasoline;
import com.android.dagger.model.DieselEngine;
import com.android.dagger.model.Engine;
import com.android.dagger.model.GasolineEngine;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class EngineModule {
    @Provides
//    @Named("Gasoline")
    @Gasoline
    public Engine provideGasoline() {
        return new GasolineEngine();
    }

    @Provides
//    @Named("Diesel")
    @Diesel
    public Engine provideDiesel() {
        return new DieselEngine();
    }
}
