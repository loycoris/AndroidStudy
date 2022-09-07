package com.android.viewbinding

import android.content.Context
import android.content.UriMatcher
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.viewbinding.databinding.FragmentFirstBinding

class FirstFragment : Fragment() {
    private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.textView2.text = "first fragment viewbinding"

        val map = mapOf("a" to 1)
        map.toList()
    }

    fun runRunnable(block: () -> Unit) {
        val runnable = Runnable {
            block()
        }
    }

}