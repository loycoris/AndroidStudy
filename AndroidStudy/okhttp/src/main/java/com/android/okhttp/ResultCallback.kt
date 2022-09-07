package com.android.okhttp

import okhttp3.Request
import java.io.IOException
import java.lang.Exception

abstract class ResultCallback {
    abstract fun onError(request: Request?, e: Exception?)

    @Throws(IOException::class)
    abstract fun onResponse(str: String?)
}