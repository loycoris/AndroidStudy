package com.android.kotlincoroutine.activity


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.android.kotlincoroutine.R
import com.android.kotlincoroutine.databinding.ActivityMainBinding
import com.android.kotlincoroutine.viewmodel.MainViewModel

class MainActivity07 : AppCompatActivity() {
    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = mainViewModel
        binding.lifecycleOwner = this
        binding.submitButton.setOnClickListener {
            mainViewModel.getUser("123")
        }
    }
}