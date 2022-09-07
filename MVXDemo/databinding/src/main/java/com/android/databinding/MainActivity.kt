package com.android.databinding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.android.databinding.databinding.ActivityMainBinding
import com.android.databinding.model.Swordsman


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val swordsman = Swordsman("张无忌", "S")
        binding.swordsman = swordsman

        binding.btLayout.setOnClickListener {
            startActivity(Intent(this, LayoutActivity::class.java))
        }

        binding.btUpdata.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }

        binding.btRecycler.setOnClickListener {
            startActivity(Intent(this, RecyclerActivity::class.java))
        }
    }
}