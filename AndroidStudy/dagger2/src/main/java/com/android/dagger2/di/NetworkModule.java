package com.android.dagger2.di;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

//Dagger模块
@Module
public class NetworkModule {
    /*@Provides
    public User providerUser() {
        return new User();
    }*/

    private Application application;

    public NetworkModule(Application application) {
        this.application = application;
    }

    @Provides
    Context provideContext() {
        return application.getApplicationContext();
    }

    //告知dagger可以通过该方法来获取到实例，模块注入实例
    @Provides
    @ApplicationScope
    public Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("http://www.baidu.com")
                .build();
    }

    @Provides
    @ApplicationScope
    public ApiService provideApiService(Retrofit retrofit) {
        return retrofit.create(ApiService.class);
    }

    @Provides
    @ApplicationScope
    public OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .build();
    }
}
