package com.android.okhttp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import java.io.File
import java.util.concurrent.TimeUnit
import android.widget.Toast
import java.io.IOException

import android.widget.Button
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import java.io.FileOutputStream
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import okhttp3.CacheControl
import java.lang.Exception


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val TAG = "OkHttp3"
    private var mOkHttpClient: OkHttpClient? = null
    private var bt_send: Button? = null
    private var bt_postsend: Button? = null
    private var bt_sendfile: Button? = null
    private var bt_downfile: Button? = null
    private var bt_cancel: Button? = null
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    val MEDIA_TYPE_MARKDOWN = "text/x-markdown; charset=utf-8".toMediaTypeOrNull()
    private val MEDIA_TYPE_PNG = "image/png".toMediaTypeOrNull()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initOkHttpClient()
        bt_send = findViewById<View>(R.id.bt_send) as Button
        bt_sendfile = findViewById<View>(R.id.bt_sendfile) as Button
        bt_postsend = findViewById<View>(R.id.bt_postsend) as Button
        bt_downfile = findViewById<View>(R.id.bt_downfile) as Button
        bt_cancel = findViewById<View>(R.id.bt_cancel) as Button
        bt_send?.setOnClickListener(this)
        bt_postsend?.setOnClickListener(this)
        bt_sendfile?.setOnClickListener(this)
        bt_downfile?.setOnClickListener(this)
        bt_cancel?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_send ->
//                getAsynHttp()
                getAsynForEngine()
            R.id.bt_postsend -> postAsynHttp()
            R.id.bt_sendfile -> postAsynFile()
            R.id.bt_downfile -> downAsynFile()
            R.id.bt_cancel -> cancel()
        }
    }

    private fun initOkHttpClient() {
        val sdcache: File? = externalCacheDir
        val cacheSize: Long = 10 * 1024 * 1024
        val builder = OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .cache(Cache(sdcache!!.getAbsoluteFile(), cacheSize))
        mOkHttpClient = builder.build()
        val client = OkHttpClient()
    }

    private fun getAsynHttp() {
        val request = Request.Builder()
            .url("http://blog.csdn.net/itachi85/")
            .method("GET", null)
            .build()
        val mcall = mOkHttpClient?.newCall(request)
        mcall?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            override fun onResponse(call: Call, response: Response) {
                val str = response.body?.string()
                Log.i(TAG, str!!)
                runOnUiThread {
                    Toast.makeText(applicationContext, "请求成功", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun getAsynForEngine() {
        OkHttpEngine.getInstance(this@MainActivity)
            .getAsynHttp("http://www.baidu.com", object : ResultCallback() {
                override fun onError(request: Request?, e: Exception?) {
                    e?.printStackTrace()
                    Toast.makeText(applicationContext, "请求失败", Toast.LENGTH_SHORT).show();
                }

                @Throws(IOException::class)
                override fun onResponse(str: String?) {
                    Log.d(TAG, str!!)
                    Toast.makeText(applicationContext, "请求成功", Toast.LENGTH_SHORT).show()
                }
            })
    }

    private fun postAsynHttp() {
        val formBody = FormBody.Builder()
            .add("ip", "123.123.123.123")
            .add("accessKey", "alibaba-inc")
            .build()
        val request = Request.Builder()
            .url("http://ip.taobao.com/outGetIpInfo")
            .post(formBody)
            .build()
        val call = mOkHttpClient?.newCall(request)
        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Toast.makeText(getApplicationContext(), "请求失败", Toast.LENGTH_SHORT).show();
            }

            override fun onResponse(call: Call, response: Response) {
                val str = response.body!!.string()
                Log.d(TAG, str)
                runOnUiThread {
                    Toast.makeText(applicationContext, "请求成功", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun postAsynFile() {
        var filepath =
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                Environment.getExternalStorageDirectory().getAbsolutePath()
            } else {
                filesDir.absolutePath
            }

        var file = File(filepath, "1.txt")
        val request = Request.Builder()
            .url("https://api.github.com/markdown/raw")
            .post(RequestBody.create(MEDIA_TYPE_MARKDOWN, file))
            .build()
        mOkHttpClient?.newCall(request)?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(getApplicationContext(), "发送失败", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onResponse(call: Call, response: Response) {
                Log.d(TAG, response.body!!.string());
            }
        })
    }

    private fun downAsynFile() {
        val url =
            "https://img-blog.csdnimg.cn/img_convert/a7c9fa905874cf30f192e184f94b8095.webp?x-oss-process=image/format,png"
        val request = Request.Builder().url(url).build()
        mOkHttpClient?.newCall(request)?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                runOnUiThread {
                    Toast.makeText(applicationContext, "文件下载失败", Toast.LENGTH_SHORT).show();
                }
            }

            override fun onResponse(call: Call, response: Response) {
                val inputStream = response.body?.byteStream()
                var fileOutputStream: FileOutputStream? = null
                try {
                    var filepath =
                        if (Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED) {
                            Environment.getExternalStorageDirectory().absolutePath
                        } else {
                            filesDir.absolutePath
                        }
                    val file = File(filepath, "ll.png")
                    if (null != file) {
                        fileOutputStream = FileOutputStream(file)
                        val buffer = ByteArray(2048)
                        var len = 0
                        while (inputStream!!.read(buffer).also { len = it } != -1) {
                            fileOutputStream.write(buffer, 0, len)
                        }
                        fileOutputStream.flush()

                        runOnUiThread {
                            Toast.makeText(applicationContext, "文件存储成功", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        runOnUiThread {
                            Toast.makeText(applicationContext, "文件存储失败", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }
        })
    }

    private fun cancel() {
        val request: Request = Request.Builder()
            .url("http://www.baidu.com")
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        val call = mOkHttpClient?.newCall(request)
        val finalCall = call
        executor.schedule(object : Runnable {
            override fun run() {
                finalCall?.cancel()
            }
        }, 100, TimeUnit.MILLISECONDS)

        call?.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {

            }

            override fun onResponse(call: Call, response: Response) {
                if (null != response.cacheResponse) {
                    val str = response.cacheResponse.toString()
                    Log.d(TAG, "cache---$str")
                } else {
                    val str = response.networkResponse.toString()
                    Log.d(TAG, "network---$str")
                }
            }
        })
    }
}