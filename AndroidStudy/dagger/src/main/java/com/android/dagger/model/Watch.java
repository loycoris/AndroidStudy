package com.android.dagger.model;

import android.util.Log;

import javax.inject.Inject;

public class Watch {
    @Inject
    public Watch() {
    }

    public void work() {
        Log.d("Dagger2", "手表工作");
    }
}
