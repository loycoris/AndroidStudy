package com.android.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface IpServiceForQueryMap {
    //@Query 动态指定查询条件组
    @GET("outGetIpInfo")
    fun getIpMsg(@QueryMap options: Map<String, String>): Call<IpModel>
}