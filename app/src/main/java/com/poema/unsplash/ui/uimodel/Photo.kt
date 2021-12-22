package com.poema.unsplash.ui.uimodel

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.poema.unsplash.R

@BindingAdapter("android:glideImages")
fun glideImages(imageView: ImageView, item: Photo) {

    Glide
        .with(imageView)
        .load(item.url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_baseline_error)
        .into(imageView)
}

data class Photo(
    val id: String,
    val name: String?,
    val description: String?,
    val url: String,
    val bestUrl: String
)
