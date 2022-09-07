package com.android.httpurl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.apache.http.NameValuePair
import java.io.IOException
import java.io.InputStream

import org.apache.http.message.BasicNameValuePair
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.entity.UrlEncodedFormEntity
import org.apache.http.client.methods.HttpPost
import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.DefaultHttpClient
import org.apache.http.params.HttpProtocolParams
import org.apache.http.protocol.HTTP
import org.apache.http.HttpVersion
import org.apache.http.client.HttpClient
import org.apache.http.params.HttpConnectionParams
import org.apache.http.params.BasicHttpParams
import org.apache.http.params.HttpParams


class MainActivity : AppCompatActivity() {
    private val TAG = "HttpUrl"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        useHttpUrlConnectionPostThread()
        useHttpClientPostThread()
//        useHttpClientGetThread()
    }

    /**
     * HttpClient GET请求网络
     */
    private fun useHttpClientGetThread() {
        Thread { useHttpClientGet("http://www.baidu.com") }.start()
    }

    /**
     * HttpClient POST请求网络
     */
    private fun useHttpClientPostThread() {
        Thread { useHttpClientPost("http://ip.taobao.com/outGetIpInfo") }.start()
    }

    /**
     * HttpUrlConnection POST请求网络
     */
    private fun useHttpUrlConnectionPostThread() {
        Thread { useHttpUrlConnectionPost("http://ip.taobao.com/outGetIpInfo") }.start()
    }


    /**
     * 设置默认请求参数，并返回HttpClient
     *
     * @return HttpClient
     */
    private fun createHttpClient(): HttpClient {
        val mDefaultHttpParams: HttpParams = BasicHttpParams()
        //设置连接超时
        HttpConnectionParams.setConnectionTimeout(mDefaultHttpParams, 15000)
        //设置请求超时
        HttpConnectionParams.setSoTimeout(mDefaultHttpParams, 15000)
        HttpConnectionParams.setTcpNoDelay(mDefaultHttpParams, true)
        HttpProtocolParams.setVersion(mDefaultHttpParams, HttpVersion.HTTP_1_1)
        HttpProtocolParams.setContentCharset(mDefaultHttpParams, HTTP.UTF_8)
        //持续握手
        HttpProtocolParams.setUseExpectContinue(mDefaultHttpParams, true)
        return DefaultHttpClient(mDefaultHttpParams)
    }

    /**
     * 使用HttpClient的get请求网络
     *
     * @param url
     */
    private fun useHttpClientGet(url: String) {
        val mHttpGet = HttpGet(url)
        mHttpGet.addHeader("Connection", "Keep-Alive")
        try {
            val mHttpClient: HttpClient = createHttpClient()
            val mHttpResponse: HttpResponse = mHttpClient.execute(mHttpGet)
            val mHttpEntity: HttpEntity = mHttpResponse.getEntity()
            val code: Int = mHttpResponse.getStatusLine().getStatusCode()
            if (null != mHttpEntity) {
                val mInputStream = mHttpEntity.content
                val respose = converStreamToString(mInputStream)
                Log.d(TAG, "请求状态码:$code\n请求结果:\n$respose")
                mInputStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun useHttpClientPost(url: String) {
        val mHttpPost = HttpPost(url)
        mHttpPost.addHeader("Connection", "Keep-Alive")
        try {
            val mHttpClient: HttpClient = createHttpClient()
            val postParams: MutableList<NameValuePair> = ArrayList()
            //要传递的参数
            postParams.add(BasicNameValuePair("ip", "123.123.123.123"))
            postParams.add(BasicNameValuePair("accessKey", "alibaba-inc"))
            mHttpPost.entity = UrlEncodedFormEntity(postParams)
            val mHttpResponse: HttpResponse = mHttpClient.execute(mHttpPost)
            val mHttpEntity: HttpEntity = mHttpResponse.getEntity()
            val code: Int = mHttpResponse.getStatusLine().getStatusCode()
            if (null != mHttpEntity) {
                val mInputStream = mHttpEntity.content
                val respose = converStreamToString(mInputStream)
                Log.d(TAG, "请求状态码:$code\n请求结果:\n$respose")
                mInputStream.close()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    /**
     * 将请求结果装潢为String类型
     *
     * @param is InputStream
     * @return String
     * @throws IOException
     */
    @Throws(IOException::class)
    private fun converStreamToString(inputStream: InputStream?): String? {
        val reader = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuffer()
        var line: String? = null
        while (reader.readLine().also { line = it } != null) {
            sb.append(
//                """
                "{$line}"

//                    """.trimIndent()
            )
        }
        return sb.toString()
    }

    private fun useHttpUrlConnectionPost(url: String) {
        var mInputStream: InputStream? = null
        val mHttpURLConnection: HttpURLConnection? = UrlConnManager.getHttpURLConnection(url)
        try {
            val postParams: MutableList<NameValuePair> = ArrayList()
            //要传递的参数
            postParams.add(BasicNameValuePair("ip", "123.123.123.123"))
            postParams.add(BasicNameValuePair("accessKey", "alibaba-inc"))
            UrlConnManager.postParams(mHttpURLConnection?.getOutputStream(), postParams)
            mHttpURLConnection?.connect()
            mInputStream = mHttpURLConnection?.getInputStream()
            val code: Int? = mHttpURLConnection?.getResponseCode()
            val respose = converStreamToString(mInputStream)
            Log.d(TAG, "请求状态码:$code\n请求结果:\n$respose")
            mInputStream?.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}