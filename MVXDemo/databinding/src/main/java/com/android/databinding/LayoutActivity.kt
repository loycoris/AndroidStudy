package com.android.databinding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.databinding.databinding.ActivityLayoutBinding
import com.android.databinding.model.Swordsman
import java.util.*


class LayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityLayoutBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_layout)
        binding.name = "风清扬"
        binding.age = 70
        binding.man = true

        val list = listOf("0", "1")
        binding.list = list

        val map = mutableMapOf("age" to "30")
        binding.map = map

        val arrays = arrayOf("张无忌", "慕容龙城")
        binding.arrays = arrays

        val swordsman = Swordsman("独孤求败", "SS")
        binding.swordsman = swordsman

        binding.time = Date()
    }
}