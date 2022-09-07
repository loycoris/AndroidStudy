package com.android.livedata2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class SecondFragment : Fragment() {
    private val mainViewModel: MyViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_second, container, false)
        val seekBar: SeekBar = root.findViewById(R.id.seekBar)

        /*val mainViewModel =
            activity?.let {
                ViewModelProvider(it).get(MyViewModel::class.java)
            }

        activity?.let {
            if (mainViewModel != null) {
                mainViewModel.progress.observe(it, Observer { seekBar.progress = it })
            }
        }*/

        mainViewModel.progress.observe(viewLifecycleOwner) {
            seekBar.progress = it
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                mainViewModel.progress.value = progress
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        return root
    }

}