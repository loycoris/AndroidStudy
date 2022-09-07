package com.android.navigation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class DetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        view?.findViewById<Button>(R.id.button2)?.setOnClickListener {
            val bundle = arguments
            if (bundle != null) {
                val args = arguments?.let { HomeFragmentArgs.fromBundle(it) }
                Log.d("lcy", "username: ${args?.userName},age: ${args?.age}")
            }

            Navigation.findNavController(it).navigate(R.id.action_detailFragment_to_homeFragment)
        }
    }

}