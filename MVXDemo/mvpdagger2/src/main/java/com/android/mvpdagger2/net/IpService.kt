package com.android.mvpdagger2.net

import com.android.mvpdagger2.model.IpInfo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface IpService {
    @FormUrlEncoded
    @POST("outGetIpInfo")
    fun getIpInfo(@Field("ip") ip: String, @Field("accessKey") accessKey: String):Observable<IpInfo>
}