package com.android.databinding3

import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

class ImageViewBindingAdapter {
    companion object {
        //加载网络图片
        @BindingAdapter("image")
        @JvmStatic
        fun setImage(imageView: ImageView, url: String) {
            Picasso.get()
                .load(url).placeholder(R.drawable.ic_launcher_background)
                .into(imageView)
        }

        //加载本地图片
        @BindingAdapter("image")
        @JvmStatic
        fun setImage(imageView: ImageView, resId: Int) {
            imageView.setImageResource(resId)
        }

        //加载网络图片
        @BindingAdapter(value = ["image", "defaultImageResource"], requireAll = false)
        @JvmStatic
        fun setImage(imageView: ImageView, url: String?, resId: Int?) {
            if (!TextUtils.isEmpty(url)) {
                Picasso.get()
                    .load(url).placeholder(R.drawable.ic_launcher_background)
                    .into(imageView)
            } else {
                resId?.let { imageView.setImageResource(it) }
            }
        }
    }
}