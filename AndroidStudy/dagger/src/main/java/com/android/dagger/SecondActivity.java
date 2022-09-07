package com.android.dagger;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.dagger.model.Swordsman;
import com.google.gson.Gson;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG="Dagger2";

    @Inject
    Gson gson;
    @Inject
    Gson gson1;
    @Inject
    Swordsman swordsman;
//    Lazy<Swordsman> swordsmanLazy;

//    @Inject
//    Student student;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
//        DaggerActivityComponent.builder().build().inject(this);
        App.get(SecondActivity.this).getActivityComponent().inject(this);
        Log.d(TAG,gson.hashCode()+"-----"+gson1.hashCode());
//        Swordsman swordsman=swordsmanLazy.get();
        swordsman.fighting();
        String sd1=swordsman.fighting();
        Log.d(TAG, "lazy---" + sd1);
    }
}
