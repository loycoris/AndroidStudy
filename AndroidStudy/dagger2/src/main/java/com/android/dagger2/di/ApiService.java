package com.android.dagger2.di;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/user/info")
    Call<String> requestInfo();
}
