package com.android.mvpsimple.net

import com.android.mvpsimple.LoadTasksCallBack
import com.android.mvpsimple.model.IpInfo


interface NetTask {
    fun execute(ip: String, callBack: LoadTasksCallBack<IpInfo>)
}