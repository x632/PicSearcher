package com.poema.unsplash.ui.uimodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.poema.unsplash.TheApp


/*@BindingAdapter("android:showOrNot")
fun showIfNotNull(textView: TextView, item: Photo) {
    textView.text = item.description ?: "by ${item.name}"
}*/

@BindingAdapter("android:glideImages")
fun glideImages(imageView: ImageView, item: Photo) {
    val context = TheApp.appContext
    Glide
        .with(context)
        .load(item.url)
        .into(imageView)
}

data class Photo(
    val id: String,
    val description: String?,
    val url: String,
    )