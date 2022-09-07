package com.android.mvpdagger2.net

import android.util.Log
import com.android.mvpdagger2.LoadTasksCallBack
import com.android.mvpdagger2.model.IpInfo
import com.android.mvpdagger2.net.IpService
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import io.reactivex.rxjava3.disposables.CompositeDisposable


class IpInfoTask : NetTask<String, IpInfo> {
    private val TAG = "lcy"
    private val HOST = "http://ip.taobao.com/"
    private var retrofit: Retrofit? = null
    private val compositeeDisposable: CompositeDisposable = CompositeDisposable()
    private var disposable: Disposable? = null

    init {
        createRetrofit()
    }

    companion object {
        var INSTANCE: IpInfoTask? = null

        fun getInstance(): IpInfoTask {
            if (INSTANCE == null) {
                INSTANCE = IpInfoTask()
            }
            return INSTANCE!!
        }
    }

    fun createRetrofit() {
        retrofit = Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    override fun execute(ip: String, loadTasksCallBack: LoadTasksCallBack<IpInfo>): Disposable {
        val ipService = retrofit?.create(IpService::class.java)
        ipService?.let {
            Thread.sleep(1000)
            it.getIpInfo(ip, "alibaba-inc")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<IpInfo> {
                    override fun onSubscribe(d: Disposable?) {
                        loadTasksCallBack.onStart()
                        disposable = d

                    }

                    override fun onNext(ipInfo: IpInfo) {
                        loadTasksCallBack.onSuccess(ipInfo)
                        Log.d(TAG, ipInfo.data.country)
                    }

                    override fun onError(e: Throwable?) {
                        loadTasksCallBack.onFailed()
                        Log.d(TAG, e?.message.toString())
                    }

                    override fun onComplete() {
                        loadTasksCallBack.onFinish()
                    }
                })
        }
        return disposable!!
    }
}