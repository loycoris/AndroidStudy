package com.android.mvpsimple.net

import android.util.Log
import com.android.mvpsimple.LoadTasksCallBack
import com.android.mvpsimple.model.IpInfo

import cn.finalteam.okhttpfinal.BaseHttpRequestCallback
import cn.finalteam.okhttpfinal.HttpRequest

import cn.finalteam.okhttpfinal.RequestParams
import com.android.mvpsimple.model.IpData


class IpInfoTask : NetTask {
    private val HOST = "http://ip.taobao.com/outGetIpInfo"

    companion object {
        var INSTANCE: IpInfoTask? = null

        fun getInstance(): IpInfoTask {
            if (INSTANCE == null) {
                INSTANCE = IpInfoTask()
            }
            return INSTANCE!!
        }
    }

    override fun execute(ip: String, loadTasksCallBack: LoadTasksCallBack<IpInfo>) {
        val requestParams = RequestParams()
        requestParams.addFormDataPart("ip", ip)
        requestParams.addFormDataPart("accessKey", "alibaba-inc")
        HttpRequest.post(HOST, requestParams, object : BaseHttpRequestCallback<IpInfo>() {
            override fun onStart() {
                super.onStart()
                loadTasksCallBack.onStart()
            }

            override fun onSuccess(ipInfo: IpInfo) {
                super.onSuccess(ipInfo)
                Log.d("lcy",ipInfo.data.toString())
                loadTasksCallBack.onSuccess(ipInfo)
            }

            override fun onFinish() {
                super.onFinish()
                loadTasksCallBack.onFinish()
            }

            override fun onFailure(errorCode: Int, msg: String) {
                super.onFailure(errorCode, msg)
                Log.d("lcy",msg)
                loadTasksCallBack.onFailed()
            }
        })
    }
}