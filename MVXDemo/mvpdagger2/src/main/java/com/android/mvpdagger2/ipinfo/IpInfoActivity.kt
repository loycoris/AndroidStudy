package com.android.mvpdagger2.ipinfo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.mvpdagger2.R
import com.android.mvpdagger2.net.IpInfoTask
import com.android.mvpdagger2.util.ActivityUtils


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
        ipInfoFragment?.setPresenter(ipInfoPresenter!!)
    }
}