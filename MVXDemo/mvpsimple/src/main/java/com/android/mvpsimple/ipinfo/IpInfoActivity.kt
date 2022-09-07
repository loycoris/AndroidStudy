package com.android.mvpsimple.ipinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.mvpsimple.R
import com.android.mvpsimple.net.IpInfoTask

import com.android.mvpsimple.util.ActivityUtils


class IpInfoActivity : AppCompatActivity() {
    private var ipInfoPresenter: IpInfoPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var ipInfoFragment =
            supportFragmentManager.findFragmentById(R.id.contentFrame) as IpInfoFragment?
        if (ipInfoFragment == null) {
            ipInfoFragment = IpInfoFragment.newInstance()
            ActivityUtils.addFragmentToActivity(
                supportFragmentManager,
                ipInfoFragment, R.id.contentFrame
            )
        }
        val ipInfoTask: IpInfoTask = IpInfoTask.getInstance()
        ipInfoPresenter = IpInfoPresenter(ipInfoFragment, ipInfoTask)
        ipInfoFragment!!.setPresenter(ipInfoPresenter!!)
    }
}