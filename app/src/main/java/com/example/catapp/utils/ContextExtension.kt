package com.example.catapp.utils

import android.content.Context
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.catapp.R


fun Context.shortToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_SHORT).show()
}

fun Context.longToast(string: String) {
    Toast.makeText(this, string, Toast.LENGTH_LONG).show()
}

fun Context.loadImage(image: Any, view: ImageView, circleState: Boolean) {
    if (circleState) {
        Glide.with(this).load(image).placeholder(R.drawable.feed_me).error(R.drawable.feed_me)
            .circleCrop().into(view)
    } else {
        Glide.with(this).load(image).placeholder(R.drawable.feed_me).error(R.drawable.feed_me)
            .into(view)
    }
}
