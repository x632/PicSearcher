package com.poema.unsplash.ui.uimodel


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.poema.unsplash.R



@BindingAdapter("android:glideImages")
fun glideImages(imageView: ImageView, item: Photo) {
    //val context = TheApp.appContext
    Glide
        .with(imageView)
        .load(item.url)
        .transition(DrawableTransitionOptions.withCrossFade())
        .error(R.drawable.ic_baseline_error_24)
        .into(imageView)
}

data class Photo(
    val id: String,
    val description: String?,
    val url: String,
)