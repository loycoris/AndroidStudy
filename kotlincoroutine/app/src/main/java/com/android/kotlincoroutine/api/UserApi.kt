package com.android.kotlincoroutine.api

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

data class User(val code: Int, val success: String)

private const val BaseUrl = "http://47.118.58.103:8080/hearingassist/"
var userapigetinfo = BaseUrl + "userapi/"

val userServiceApi: UserServiceApi by lazy {
    val retrofit = Retrofit.Builder()
        .client(OkHttpClient.Builder().addInterceptor {
            it.proceed(it.request().apply {
                Log.d("jason", "requesr:${it.request().url()}")
            })
        }.build())
        .baseUrl(userapigetinfo)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    retrofit.create(UserServiceApi::class.java)
}

interface UserServiceApi {
    @GET("getinfo")
    fun loadUser(@Query("name") name: String): Call<User>

    @GET("getinfo")
    suspend fun getUser(@Query("name") name: String): User
}