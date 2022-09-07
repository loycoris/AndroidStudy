package com.android.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface IpServiceForPath {
    //@PATH 动态配置URL地址
    @GET("{path}/getIpInfo.php?ip=59.108.54.37")
    fun getIpMsg(@Path("path") path: String?): Call<IpModel?>?
}