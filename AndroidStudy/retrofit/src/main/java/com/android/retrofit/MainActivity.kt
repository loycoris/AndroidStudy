package com.android.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import android.widget.Toast
import android.view.View
import android.widget.Button


class MainActivity : AppCompatActivity() {
    private var bt_request: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bt_request = findViewById<View>(R.id.bt_request) as Button
        bt_request?.setOnClickListener {
//            getIpInformationForQuery("123.123.123.123", "alibaba-inc")
//            getIpInformation()
//            getIpInformationForPath("service")
            postIpInformation("123.123.123.123", "alibaba-inc")
//            postIpInformationForBody("123.123.123.123", "alibaba-inc")
        }
    }

    private fun getIpInformation() {
        val url = "http://ip.taobao.com/"
        val retrofit: Retrofit? = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ipService = retrofit?.create(IpService::class.java) //动态代理
        ipService?.getIpMsg()?.enqueue(object : Callback<IpModel?> {
            override fun onResponse(call: Call<IpModel?>, response: Response<IpModel?>) {
                val country: String? = response.body()?.data?.country
                Log.i("lcy", "country: $country")
                Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<IpModel?>, t: Throwable) {
            }
        })
    }

    /**
     * @param path
     * @Path方式GET请求
     */
    private fun getIpInformationForPath(path: String) {
        val url = "http://ip.taobao.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ipService = retrofit.create(IpServiceForPath::class.java)
        val call = ipService.getIpMsg(path)
        call!!.enqueue(object : Callback<IpModel?> {
            override fun onResponse(call: Call<IpModel?>, response: Response<IpModel?>) {
                val country = response.body()!!.data.country
                Log.i("lcy", "country$country")
                Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<IpModel?>, t: Throwable) {

            }
        })
    }

    /**
     * @param ip
     * @Query方式GET请求
     */
    private fun getIpInformationForQuery(ip: String, accessKey: String) {
        val url = "http://ip.taobao.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ipService = retrofit.create(IpServiceForQuery::class.java)
        val call = ipService.getIpMsg(ip, accessKey)
        call.enqueue(object : Callback<IpModel> {
            override fun onResponse(call: Call<IpModel>, response: Response<IpModel>) {
                val country = response.body()!!.data.country
                Log.i("lcy", "country$country")
                Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<IpModel>, t: Throwable) {}
        })
    }

    /**
     * 传输数据类型为键值对的POST请求
     *
     * @param ip
     */
    private fun postIpInformation(ip: String, accessKey: String) {
        val url = "http://ip.taobao.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ipService = retrofit.create(IpServiceForPost::class.java)
        val call = ipService.getIpMsg(ip, accessKey)
        call.enqueue(object : Callback<IpModel> {
            override fun onResponse(call: Call<IpModel>, response: Response<IpModel>) {
                val country = response.body()!!.data.country
                Log.i("lcy", "country$country")
                Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<IpModel>, t: Throwable) {}
        })
    }

    /**
     * 传输数据类型Json字符串的POST请求
     *
     * @param ip
     */
    private fun postIpInformationForBody(ip: String, accessKey: String) {
        val url = "http://ip.taobao.com/"
        val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val ipService: IpServiceForPostBody = retrofit.create(IpServiceForPostBody::class.java)
        val call: Call<IpModel> = ipService.getIpMsg(Ip(ip, accessKey))
        call.enqueue(object : Callback<IpModel> {
            override fun onResponse(call: Call<IpModel>, response: Response<IpModel>) {
                val country = response.body()!!.data.country
                Log.i("lcy", "country$country")
                Toast.makeText(applicationContext, country, Toast.LENGTH_SHORT).show()
            }

            override fun onFailure(call: Call<IpModel>, t: Throwable) {
                Log.i("lcy", t.message.toString())
            }
        })
    }
}