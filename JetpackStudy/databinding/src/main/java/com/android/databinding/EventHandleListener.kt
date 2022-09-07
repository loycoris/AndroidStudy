package com.android.databinding

import android.content.Context
import android.view.View
import android.widget.Toast

class EventHandleListener(val context: Context) {
    fun buttonOnclick(view: View) {
        Toast.makeText(context, "Like", Toast.LENGTH_LONG).show()
    }
}