package com.android.rxjava.net

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.rxjava.R
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.*
import java.io.IOException


class OkhttpActivity : AppCompatActivity() {
    private val TAG = "RxJava"
    private var mOkHttpClient: OkHttpClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)
    }

    override fun onResume() {
        super.onResume()
        postAsynHttp("59.108.54.37")
    }

    private fun postAsynHttp(size: String) {
        getObservable(size)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<String> {
                override fun onSubscribe(d: Disposable?) {
                    Log.d(TAG, "onSubscribe")
                }

                override fun onNext(t: String?) {
                    Log.d(TAG, t!!)
                    Toast.makeText(getApplicationContext(), "请求成功", Toast.LENGTH_SHORT).show()
                }

                override fun onError(e: Throwable?) {
                    Log.d(TAG, e?.message.toString())
                }

                override fun onComplete() {
                    Log.d(TAG, "onComplete")
                }
            })
    }

    private fun getObservable(ip: String): Observable<String> {
        val observable = Observable.create(ObservableOnSubscribe<String> { emitter ->
            mOkHttpClient = OkHttpClient()
            val formBody = FormBody.Builder()
                .add("ip", ip)
                .add("app_id", "qikblljkqjfvagjk")
                .add("app_secret", "blRZckxOU0Y2emEzNk1CZFZqV2RGUT09")
                .build()
            val request = Request.Builder()
                .url("https://www.mxnzp.com/api/ip/aim_ip")
                .post(formBody)
                .build()
            val call = mOkHttpClient?.newCall(request)
            call?.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    emitter.onError(e)
                }

                override fun onResponse(call: Call, response: Response) {
                    val str = response.body!!.string()
                    emitter.onNext(str)
                    emitter.onComplete()
                }
            })
        })
        return observable
    }
}