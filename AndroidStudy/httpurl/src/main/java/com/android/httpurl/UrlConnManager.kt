package com.android.httpurl

import android.text.TextUtils
import org.apache.http.NameValuePair
import java.io.BufferedWriter

import java.io.IOException
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder

class UrlConnManager {
    /**
     * 配置默认参数
     * @param url
     * @return HttpURLConnection
     */
    companion object {
        fun getHttpURLConnection(url: String?): HttpURLConnection? {
            var mHttpURLConnection: HttpURLConnection? = null
            try {
                var mUrl = URL(url)
                mHttpURLConnection = (mUrl.openConnection() as HttpURLConnection).apply {
                    connectTimeout = 15000
                    readTimeout = 15000
                    requestMethod = "POST"
                    setRequestProperty("Connection", "Keep-Alive")
                    doInput = true
                    doOutput = true
                }
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return mHttpURLConnection
        }

        /**
         * 组织请求参数,并将参数写入到输出流
         */
        @Throws(IOException::class)
        fun postParams(output: OutputStream?, paramsList: List<NameValuePair>) {
            var mStringBuilder = StringBuilder()
            for (pair in paramsList) {
                if (!TextUtils.isEmpty(mStringBuilder)) {
                    mStringBuilder.append("&")
                }
                mStringBuilder.apply {
                    append(URLEncoder.encode(pair.name, "UTF-8"))
                    append("=")
                    append(URLEncoder.encode(pair.value, "UTF-8"))
                }
            }
            BufferedWriter(OutputStreamWriter(output, "UTF-8")).apply {
                write(mStringBuilder.toString())
                flush()
                close()
            }
        }
    }
}