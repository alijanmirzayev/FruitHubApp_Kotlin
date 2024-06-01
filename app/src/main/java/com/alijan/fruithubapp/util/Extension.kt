package com.alijan.fruithubapp.util

import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

fun ImageView.setImageFromUrl(url: String?) {
    Glide
        .with(this)
        .load(url)
        .fitCenter()
        .into(this)
}

