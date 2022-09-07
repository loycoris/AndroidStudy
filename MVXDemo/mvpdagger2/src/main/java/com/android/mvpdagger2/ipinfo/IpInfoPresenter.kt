package com.android.mvpdagger2.ipinfo

import android.util.Log
import com.android.mvpdagger2.LoadTasksCallBack
import com.android.mvpdagger2.model.IpInfo
import com.android.mvpdagger2.net.IpInfoTask
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable


class IpInfoPresenter(_addTaskView: IpInfoFragment, _netTask: IpInfoTask) :
    IpInfoContract.Presenter, LoadTasksCallBack<IpInfo> {
    val netTask = _netTask
    val addTaskView = _addTaskView

    private var disposable: Disposable? = null
    private val compositeeDisposable: CompositeDisposable = CompositeDisposable()

    override fun getIpInfo(ip: String) {
        disposable = netTask?.execute(ip, this)
        subscribe()
    }

    override fun subscribe() {
        disposable?.let { compositeeDisposable.add(disposable) }
        Log.d("lcy", "subscribe")
    }

    override fun unsubscribe() {
        compositeeDisposable?.let {
            disposable?.dispose()
            compositeeDisposable.clear()
            Log.d("lcy", "unsubscribe")
        }
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