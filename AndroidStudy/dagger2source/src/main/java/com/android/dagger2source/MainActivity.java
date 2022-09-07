package com.android.dagger2source;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.dagger2source.component.DaggerActivityComponent;
import com.android.dagger2source.model.User;
import com.android.dagger2source.model.Watch;

import javax.inject.Inject;

public class MainActivity extends AppCompatActivity {
    /*@Inject
    Watch watch;*/

    @Inject
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerActivityComponent.create().inject(this);
        /*String sr = watch.work();
        Log.i("lcy", sr);*/
        Log.i("lcy", "user: " + user.hashCode());
    }
}