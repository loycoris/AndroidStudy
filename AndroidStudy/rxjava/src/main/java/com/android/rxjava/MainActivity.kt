package com.android.rxjava

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.content.Intent
import com.android.rxjava.net.OkhttpActivity
import com.android.rxjava.net.RetrofitActivity


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private var bt_okhttp: Button? = null
    private var bt_create: Button? = null
    private var bt_filter: Button? = null
    private var bt_transform: Button? = null
    private var bt_combine: Button? = null
    private var bt_utility: Button? = null
    private var bt_error: Button? = null
    private var bt_conditional: Button? = null
    private var bt_conversion: Button? = null
    private var bt_retrofit: Button? = null
    private var bt_rxbus: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
    }

    private fun initView() {
        bt_create = findViewById<View>(R.id.bt_create) as Button
        bt_transform = findViewById<View>(R.id.bt_transform) as Button
        bt_filter = findViewById<View>(R.id.bt_filter) as Button
        bt_combine = findViewById<View>(R.id.bt_combine) as Button
        bt_utility = findViewById<View>(R.id.bt_utility) as Button
        bt_error = findViewById<View>(R.id.bt_error) as Button
        bt_conditional = findViewById<View>(R.id.bt_conditional) as Button
        bt_conversion = findViewById<View>(R.id.bt_conversion) as Button
        bt_okhttp = findViewById<View>(R.id.bt_okhttp) as Button
        bt_retrofit = findViewById<View>(R.id.bt_retrofit) as Button
        bt_rxbus = findViewById<View>(R.id.bt_rxbus) as Button
        bt_filter?.setOnClickListener(this)
        bt_create?.setOnClickListener(this)
        bt_transform?.setOnClickListener(this)
        bt_combine?.setOnClickListener(this)
        bt_utility?.setOnClickListener(this)
        bt_error?.setOnClickListener(this)
        bt_conditional?.setOnClickListener(this)
        bt_conversion?.setOnClickListener(this)
        bt_okhttp?.setOnClickListener(this)
        bt_retrofit?.setOnClickListener(this)
        bt_rxbus?.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.bt_create -> {
                val intent = Intent(this@MainActivity, CreateActivity::class.java)
                startActivity(intent)
            }
            R.id.bt_transform -> {
                val transformIntent = Intent(this@MainActivity, TransformActivity::class.java)
                startActivity(transformIntent)
                Log.d("lcy", "onclick")
            }
            R.id.bt_filter -> {
                val filterIntent = Intent(this@MainActivity, FilterActivity::class.java)
                startActivity(filterIntent)
            }
            R.id.bt_combine -> {
                val combineIntent = Intent(this@MainActivity, CombineActivity::class.java)
                startActivity(combineIntent)
            }
            R.id.bt_utility -> {
                val utilityIntent = Intent(this@MainActivity, UtilityActivity::class.java)
                startActivity(utilityIntent)
            }
            R.id.bt_error -> {
                val errorIntent = Intent(this@MainActivity, ErrorActivity::class.java)
                startActivity(errorIntent)
            }
            R.id.bt_conditional -> {
                val conditionalIntent = Intent(this@MainActivity, ConditionalActivity::class.java)
                startActivity(conditionalIntent)
            }
            R.id.bt_conversion -> {
                val conversionIntent = Intent(this@MainActivity, ConversionActivity::class.java)
                startActivity(conversionIntent)
            }
            R.id.bt_okhttp -> {
                val okhttpIntent = Intent(this@MainActivity, OkhttpActivity::class.java)
                startActivity(okhttpIntent)
            }
            R.id.bt_retrofit -> {
                val retrofitIntent = Intent(this@MainActivity, RetrofitActivity::class.java)
                startActivity(retrofitIntent)
            }
            R.id.bt_rxbus -> {
//                val rxbusIntent = Intent(this@MainActivity, RxBusActivity::class.java)
//                startActivity(rxbusIntent)
            }
        }
    }
}