package com.android.rxjava.net

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import com.android.rxjava.*
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast
import io.reactivex.rxjava3.disposables.CompositeDisposable
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory


class RetrofitActivity : AppCompatActivity() {
    private val TAG = "RxJava"

//        private val subscription: Subscription? = null
    private val compositeeDisposable: CompositeDisposable = CompositeDisposable()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit)
    }

    override fun onResume() {
        super.onResume()
        postIpInformation("59.108.54.37", "alibaba-inc")
    }

    private fun postIpInformation(ip: String, accessKey: String) {
        val url = "http://ip.taobao.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
        val ipService = retrofit.create(IpServiceForPost::class.java)
        /*ipService.getIpMsg(ip, accessKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<IpModel> {
                override fun onError(e: Throwable?) {
                    Log.d(TAG, e?.message.toString())
                }

                override fun onNext(ipModel: IpModel?) {
                    val country = ipModel?.data?.country
                    Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
                }

                override fun onSubscribe(d: Disposable?) {
//                    compositeSubscription.add(d)
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }
            })*/
        ipService.getIpMsg(ip, accessKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<HttpResult<IpData>> {
                override fun onSubscribe(d: Disposable?) {
                    compositeeDisposable.add(d)
                }

                override fun onNext(ipDataHttpResult: HttpResult<IpData>?) {
                    val country = ipDataHttpResult?.data?.country
                    Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, e?.message.toString())
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }
            })
    }

    override fun onStop() {
        super.onStop()
        compositeeDisposable?.let { compositeeDisposable.clear() }
    }
}