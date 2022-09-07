package com.android.retrofit

import retrofit2.Call
import retrofit2.http.*

interface IpServiceForPost {
    //@Field 传输数据类型为键值对
    @FormUrlEncoded
    @POST("outGetIpInfo")
    fun getIpMsg(@Field("ip") ip: String, @Field("accessKey") accessKey: String): Call<IpModel>
}