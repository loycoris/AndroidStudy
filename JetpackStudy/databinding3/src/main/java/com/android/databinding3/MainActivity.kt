package com.android.databinding3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.databinding3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
//        binding.networkImage = "https://t7.baidu.com/it/u=963301259,1982396977&fm=193&f=GIF"
        binding.localImage = R.mipmap.ic_launcher_round
    }
}