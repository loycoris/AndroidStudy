package com.android.mvpdagger2.ipinfo

import com.android.mvpdagger2.BasePresenter
import com.android.mvpdagger2.BaseView
import com.android.mvpdagger2.model.IpInfo

interface IpInfoContract {
    interface Presenter : BasePresenter {
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