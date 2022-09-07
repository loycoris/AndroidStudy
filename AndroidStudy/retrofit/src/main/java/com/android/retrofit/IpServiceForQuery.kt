package com.android.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface IpServiceForQuery {
    //@Query 动态指定查询条件
    @GET("outGetIpInfo")
    fun getIpMsg(@Query("ip") ip: String, @Query("accessKey") accessKey: String): Call<IpModel>
}