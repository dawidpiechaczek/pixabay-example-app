package com.appsirise.pixabayexampleapp.photos.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, previewUrl: String?) {
    Glide.with(view)
        .load(previewUrl)
        //  .placeholder() - for case when banner is empty or error occurs
        .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
        .into(view)
}
