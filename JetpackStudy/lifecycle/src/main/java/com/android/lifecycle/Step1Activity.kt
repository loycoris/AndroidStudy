package com.android.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Chronometer

class Step1Activity : AppCompatActivity() {
    lateinit var chronometer: Chronometer
    var elapsedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        chronometer = findViewById(R.id.chronometer)

    }

    override fun onResume() {
        super.onResume()
        chronometer.base = SystemClock.elapsedRealtime() - elapsedTime
        chronometer.start()
    }

    override fun onPause() {
        super.onPause()
        elapsedTime = SystemClock.elapsedRealtime() - chronometer.base
        chronometer.stop()
    }
}