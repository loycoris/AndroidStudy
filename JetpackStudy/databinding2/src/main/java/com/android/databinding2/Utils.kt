package com.android.databinding2

class Utils {
    companion object {
        @JvmStatic
        fun getStar(star: Int): String {
            when (star) {
                1 -> return "一星"
                2 -> return "二星"
                else -> return ""
            }
        }
    }
}