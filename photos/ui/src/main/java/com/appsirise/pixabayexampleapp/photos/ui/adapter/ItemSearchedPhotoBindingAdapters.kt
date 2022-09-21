package com.appsirise.pixabayexampleapp.photos.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.appsirise.pixabayexampleapp.photos.ui.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, previewUrl: String?) {
    Glide.with(view)
        .load(previewUrl)
        .placeholder(R.drawable.ic_camera)
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(view)
}
