package com.android.hilt;


import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import javax.inject.Inject;

import dagger.hilt.android.qualifiers.ApplicationContext;

public class ViewModel{
    User user;

    //预定义绑定
    Application application;

    Activity activity;

    Context contex;

//    @Inject
    public ViewModel(User user, Application application, Activity activity,Context contex) {
        this.user = user;
        this.application = application;
        this.activity = activity;
        this.contex = contex;
    }

    void test() {
        Log.d("TAG", "test:user = " + user);
        Log.d("TAG", "test:application = " + application);
        Log.d("TAG", "test:activity = " + activity);
        Log.d("TAG", "test:contex = " + contex);
    }
}
