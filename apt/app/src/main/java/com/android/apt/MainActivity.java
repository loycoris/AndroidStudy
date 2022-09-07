package com.android.apt;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.annotation.Inject;

public class MainActivity extends AppCompatActivity {

    @Inject("zhangsan")
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}