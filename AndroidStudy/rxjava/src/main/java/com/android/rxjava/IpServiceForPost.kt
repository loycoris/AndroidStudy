package com.android.rxjava

import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.POST
import retrofit2.http.FormUrlEncoded

data class HttpResult<T>(val code: Int,val msg: String, val data: T)

interface IpServiceForPost {
    //@Field 传输数据类型为键值对
    @FormUrlEncoded
    @POST("outGetIpInfo")
//    fun getIpMsg(@Field("ip") ip: String, @Field("accessKey") accessKey: String): Observable<IpModel>
    fun getIpMsg(@Field("ip") ip: String, @Field("accessKey") accessKey: String): Observable<HttpResult<IpData>>
}