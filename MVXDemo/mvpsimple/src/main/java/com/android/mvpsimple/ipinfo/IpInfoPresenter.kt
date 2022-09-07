package com.android.mvpsimple.ipinfo

import com.android.mvpsimple.LoadTasksCallBack
import com.android.mvpsimple.model.IpInfo
import com.android.mvpsimple.net.IpInfoTask


class IpInfoPresenter(_addTaskView: IpInfoFragment, _netTask: IpInfoTask) :
    IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    val netTask = _netTask
    val addTaskView = _addTaskView

    override fun getIpInfo(ip: String) {
        netTask?.execute(ip, this)
    }

    override fun onSuccess(ipInfo: IpInfo) {
        addTaskView?.isActive().let { addTaskView?.setIpInfo(ipInfo) }
    }

    override fun onStart() {
        addTaskView?.isActive().let { addTaskView?.showLoading() }
    }

    override fun onFailed() {
        addTaskView?.isActive()
            .let {
                addTaskView?.showError()
                addTaskView?.hideLoading()
            }
    }

    override fun onFinish() {
        addTaskView?.isActive().let { addTaskView?.hideLoading() }
    }
}