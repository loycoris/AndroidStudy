package com.android.dagger;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.dagger.component.DaggerActivityComponent;
import com.android.dagger.model.Man;
import com.android.dagger.model.Car;
import com.android.dagger.model.Watch;
import com.google.gson.Gson;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "Dagger2";
    private Button bt_jump;

//    @Inject
//    User user;

    @Inject
    Watch watch;
    @Inject
    Watch watch1;
    @Inject
    Gson gson;
    @Inject
    Gson gson1;
    @Inject
    Car car;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerApplicationComponent.create().inject(this);
//        System.out.println(user);

//        DaggerActivityComponent.create().inject(this);
        App.get(MainActivity.this).getActivityComponent().inject(this);
        onClick();
        watch.work();

        String jsonData = "{'name':'liuliuliu','age':'18'}";
        Man man = gson.fromJson(jsonData, Man.class);
        Log.d(TAG, "name---" + man.getName());

        String str = car.run();
        Log.d(TAG, "car---" + str);
        Log.d(TAG, gson.hashCode() + "---------" + gson1.hashCode());
        Log.d(TAG, watch.hashCode() + "---------" + watch1.hashCode());
    }

    private void onClick() {
        bt_jump = (Button) findViewById(R.id.bt_jump);
        bt_jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
    }
}