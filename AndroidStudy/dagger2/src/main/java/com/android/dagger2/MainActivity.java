package com.android.dagger2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.dagger2.di.ApiService;
import com.android.dagger2.di.ApplicationComponent;
import com.android.dagger2.di.DaggerUserComponent;
import com.android.dagger2.di.User;
import com.android.dagger2.di.UserComponent;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject
    User user;
    @Inject
    User user1;

    @Inject
    Retrofit retrofit;

    @Inject
    ApiService apiService;
    @Inject
    ApiService apiService1;

    UserComponent userComponent;

    @Inject
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        DaggerApplicationComponent.create().inject(this);
//        MyApplication.getApplicationComponent().inject(this);

        userComponent = DaggerUserComponent.builder().applicationComponent(MyApplication.getApplicationComponent()).build();
        userComponent.inject(this);
        Log.d("lcy", "user = " + user.hashCode());
        Log.d("lcy", user.hashCode() + "------" + user1.hashCode());
        Log.d("lcy", "retrofit = " + retrofit.hashCode());
        Log.d("lcy", "apiService = " + apiService.hashCode());
        Log.d("lcy", apiService.hashCode() + "------" + apiService1.hashCode());

        Log.d("lcy", "context = " + context);
        startActivity(new Intent(this, SecondActivity.class));
    }
}