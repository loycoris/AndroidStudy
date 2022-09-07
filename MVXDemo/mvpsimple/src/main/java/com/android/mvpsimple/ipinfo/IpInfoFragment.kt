package com.android.mvpsimple.ipinfo

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.android.mvpsimple.model.IpInfo
import android.widget.TextView
import com.android.mvpsimple.R
import com.android.mvpsimple.ipinfo.IpInfoContract.Presenter
import android.app.ProgressDialog
import android.util.Log
import android.widget.Toast
import com.android.mvpsimple.model.IpData


class IpInfoFragment : Fragment(), IpInfoContract.View {
    private var tv_country: TextView? = null
    private var tv_area: TextView? = null
    private var tv_city: TextView? = null
    private var bt_ipinfo: Button? = null
    private var mDialog: Dialog? = null
    private var mPresenter: Presenter? = null

    companion object {
        fun newInstance(): IpInfoFragment {
            return IpInfoFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_ipinfo, container, false)
        tv_country = root.findViewById<View>(R.id.tv_country) as TextView
        tv_area = root.findViewById<View>(R.id.tv_area) as TextView
        tv_city = root.findViewById<View>(R.id.tv_city) as TextView
        bt_ipinfo = root.findViewById<View>(R.id.bt_ipinfo) as Button
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        super.onActivityCreated(savedInstanceState)
        mDialog = ProgressDialog(activity)
        (mDialog as ProgressDialog).setTitle("获取数据中")
        bt_ipinfo!!.setOnClickListener { mPresenter!!.getIpInfo("39.155.184.147") }
    }

    override fun setPresenter(presenter: IpInfoContract.Presenter) {
        mPresenter = presenter
    }

    override fun setIpInfo(ipInfo: IpInfo) {
        if (ipInfo != null && ipInfo.data != null) {
            val ipdata: IpData = ipInfo.data
            tv_country!!.text = ipdata.country
            tv_area!!.text = ipdata.area
            tv_city!!.text = ipdata.city
            Log.d("lcy",ipdata.country)
        }
    }

    override fun showLoading() {
        mDialog?.show()
    }

    override fun hideLoading() {
        mDialog?.isShowing.let { mDialog?.dismiss() }
    }

    override fun showError() {
        Toast.makeText(activity?.applicationContext, "网络出错", Toast.LENGTH_SHORT).show();
    }

    override fun isActive(): Boolean {
        return isAdded()
    }
}