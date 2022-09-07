package com.android.lifecycle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer

class Step3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_2)

    }

    fun startGps(view: View) {
        startService(Intent(this,MyLocationService::class.java))
    }
    fun stopGps(view: View) {
        stopService(Intent(this,MyLocationService::class.java))
    }
}