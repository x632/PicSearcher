package com.poema.unsplash.data.model

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.poema.unsplash.TheApp


@BindingAdapter("android:showOrNot")
fun showIfNotNull(textView: TextView, item: String?) {
    if(item==null)
        textView.visibility = View.GONE
    else textView.visibility= View.VISIBLE
}

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