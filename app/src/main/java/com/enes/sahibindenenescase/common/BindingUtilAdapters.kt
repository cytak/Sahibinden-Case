package com.enes.sahibindenenescase.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.enes.sahibindenenescase.R


@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) return
    Glide.with(this)
        .load(url)
        .placeholder(R.drawable.ic_hourglass)
        .error(R.drawable.ic_broken_image)
        .into(this)
}

@BindingAdapter("resources")
fun ImageView.loadImage(resource: Int?) {
    if (resource == 0 ) return
    Glide.with(this)
        .load(resource)
        .placeholder(R.drawable.ic_hourglass)
        .error(R.drawable.ic_broken_image)
        .into(this)
}

