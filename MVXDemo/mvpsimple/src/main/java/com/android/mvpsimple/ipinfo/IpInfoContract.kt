package com.android.mvpsimple.ipinfo

import com.android.mvpsimple.BaseView
import com.android.mvpsimple.model.IpInfo


interface IpInfoContract {
    interface Presenter {
        fun getIpInfo(ip: String)
    }

    interface View : BaseView<Presenter> {
        fun setIpInfo(ipInfo: IpInfo)
        fun showLoading()
        fun hideLoading()
        fun showError()
        fun isActive(): Boolean
    }
}