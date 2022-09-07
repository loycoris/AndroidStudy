package com.android.okhttp

import android.content.Context
import android.os.Handler
import okhttp3.*
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit


class OkHttpEngine private constructor(context: Context) {
    private var mOkHttpClient: OkHttpClient? = null
    private var mHandler: Handler? = null

    init {
        val sdcache: File? = context.externalCacheDir
        val cacheSize: Long = 10 * 1024 * 1024
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(Cache(sdcache!!.getAbsoluteFile(), cacheSize))
        mOkHttpClient = builder.build()
        mHandler = Handler()
    }

    companion object : SingletonHolder<OkHttpEngine, Context>(::OkHttpEngine)
    //通过@JvmStatic注解，使得在Java中调用instance直接是像调用静态函数一样，
    //类似OkHttpEngine.getInstance(),如果不加注解，在Java中必须这样调用: OkHttpEngine.Companion.getInstance().
    //@JvmStatic
    //使用lazy属性代理，并指定LazyThreadSafetyMode为SYNCHRONIZED模式保证线程安全

    fun getAsynHttp(url: String, callback: ResultCallback) {
        val request = Request.Builder()
            .url(url)
            .build()
        val call = mOkHttpClient?.newCall(request)
        dealResult(call, callback)
    }

    private fun dealResult(call: Call?, callback: ResultCallback) {
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                sendFailedCallback(call.request(), e, callback);
            }

            override fun onResponse(call: Call, response: Response) {
                sendSuccessCallback(response.body?.string(), callback);
            }

            private fun sendSuccessCallback(str: String?, callback: ResultCallback) {
                mHandler!!.post {
                    if (callback != null) {
                        try {
                            callback.onResponse(str)
                        } catch (e: IOException) {
                            e.printStackTrace()
                        }
                    }
                }
            }

            private fun sendFailedCallback(
                request: Request,
                e: IOException,
                callback: ResultCallback
            ) {
                mHandler!!.post { if (callback != null) callback.onError(request, e) }
            }
        })
    }
}