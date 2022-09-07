package com.android.databinding7

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.android.databinding7.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewmodel = viewModel

        viewModel.aTeamScore.observe(this) {
            binding.textView3.text = it.toString()
        }

        viewModel.bTeamScore.observe(this) {
            binding.textView4.text = it.toString()
        }
    }
}