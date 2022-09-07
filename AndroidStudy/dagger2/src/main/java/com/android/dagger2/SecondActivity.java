package com.android.dagger2;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;


import com.android.dagger2.di.ApiService;
import com.android.dagger2.di.Student;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "lcy";

    /*@Inject
    ApiService apiService;*/

    @Inject
    Student student;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

//        DaggerApplicationComponent.create().inject(this);
//        MyApplication.getApplicationComponent().inject(this);
//        Log.d("lcy", "apiService3 = " + apiService.hashCode());
        MyApplication.getApplicationComponent().studentComponent().create().inject(this);
        Log.d("lcy", "student = " + student.hashCode());

    }
}
