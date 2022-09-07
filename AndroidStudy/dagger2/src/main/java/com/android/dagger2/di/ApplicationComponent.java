package com.android.dagger2.di;

import android.content.Context;

import com.android.dagger2.MainActivity;
import com.android.dagger2.SecondActivity;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;

@Component(modules = {NetworkModule.class, SubComponentModule.class})
@ApplicationScope
public interface ApplicationComponent {

    //    void inject(MainActivity mainActivity);
    Context getCntext();

    Retrofit getRetrofit();

    ApiService getApiService();

    StudentComponent.Factory studentComponent();

//    StudentComponent buildStudentComponent();

//    void inject(SecondActivity secondActivity);
}
