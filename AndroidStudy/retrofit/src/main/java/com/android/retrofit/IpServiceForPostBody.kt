package com.android.retrofit

import retrofit2.Call
import retrofit2.http.*

interface IpServiceForPostBody {
    //@Body 传输数据类型为Json字符串
    @POST("outGetIpInfo")
    fun getIpMsg(@Body ip: Ip): Call<IpModel>
}