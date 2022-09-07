package com.android.databinding.Utils

import androidx.databinding.BindingConversion

import com.android.databinding.model.Swordsman
import java.text.SimpleDateFormat
import java.util.*

//单例类，类似java中的静态方法，通过类名.方法直接调用
object Utils {
    @JvmStatic//需要添加该注解，否则xml中找不到该方法
    fun getName(swordsman: Swordsman): String {
        return swordsman.name
    }

    @BindingConversion
    @JvmStatic
    fun convertDate(date: Date?): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        return sdf.format(date)
    }
}