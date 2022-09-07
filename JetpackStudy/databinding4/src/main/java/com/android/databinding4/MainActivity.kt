package com.android.databinding4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.android.databinding4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    val users = Users()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        val userViewmodel = UserViewmodel(User("Jack"))
        binding.userViewModel = userViewmodel

        binding.userObF = ObFUser()


        binding.user = users
        Log.d("lcy", "username = ${users.name}")

        val mEQTypeShow = listOf<String>("EQ1", "EQ2")
        //将可选内容与ArrayAdapter连接起来
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_spinner_item,
            mEQTypeShow
        )
        //设置下拉列表的风格
        //设置下拉列表的风格
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        //将adapter 添加到spinner中
        //将adapter 添加到spinner中
        binding.spinner.setAdapter(adapter)
        //添加事件Spinner事件监听
        //添加事件Spinner事件监听
        binding.spinner.onItemSelectedListener = SpinnerSelectedListener()
        //设置默认值
        //设置默认值
        binding.spinner.setVisibility(View.VISIBLE)
    }

    inner class SpinnerSelectedListener : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            Log.d("lcy", "username = ${users.name}")
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

    }
}

