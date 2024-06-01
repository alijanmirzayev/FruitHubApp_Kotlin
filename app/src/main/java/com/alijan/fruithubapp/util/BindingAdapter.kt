package com.alijan.fruithubapp.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("setImageFromUrl")
fun setImageFromUrl(imageView: ImageView, url: String?){
    url?.let {
        imageView.setImageFromUrl(url)
    }
}