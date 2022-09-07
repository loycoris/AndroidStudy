package com.android.mvpsimple.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

class ActivityUtils {
    companion object {
        fun addFragmentToActivity(fragmentManager: FragmentManager, fragment: Fragment, frameId: Int) {
            val transaction: FragmentTransaction = fragmentManager.beginTransaction()
            transaction.add(frameId, fragment)
            transaction.commit()
        }
    }
}