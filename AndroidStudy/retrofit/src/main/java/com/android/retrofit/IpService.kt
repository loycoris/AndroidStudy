package com.android.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers

interface IpService {
    @Headers("Accept-Encoding: application/json", "User-Agent: MoonRetrofit")
    @GET("outGetIpInfo?ip=123.123.123.123&accessKey=alibaba-inc")
    fun getIpMsg(): Call<IpModel?>?
}