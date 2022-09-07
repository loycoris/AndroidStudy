package com.android.dagger.model;

import com.android.dagger.annotation.Gasoline;
import com.android.dagger.model.Engine;

import javax.inject.Inject;
import javax.inject.Named;

public class Car {
    private Engine engine;

//    @Inject
//    public Car(@Named("Diesel") Engine engine) {
//        this.engine = engine;
//    }

    @Inject
    public Car(@Gasoline Engine engine) {
        this.engine = engine;
    }

    public String run() {
        return engine.work();
    }
}
