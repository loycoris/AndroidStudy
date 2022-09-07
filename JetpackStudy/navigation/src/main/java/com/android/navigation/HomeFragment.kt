package com.android.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val button = view?.findViewById<Button>(R.id.button)
        button?.setOnClickListener {
            val args = HomeFragmentArgs.Builder()
                .setUserName("rose")
                .setAge(18)
                .build().toBundle()
            val navController = Navigation.findNavController(it)
            navController.navigate(R.id.action_homeFragment_to_detailFragment, args)
        }
    }
}