package com.poema.unsplash.ui.uimodel

import android.widget.ImageView
import android.widget.TextView
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

@BindingAdapter("android:fixDescription")
fun fixDescription(textView: TextView, item: Photo) {
    var processingString = ""
    val ch = ' '
    var lastString = ""
    item.description?.let {
        if (item.description!!.length > 48
        ) {
            processingString  = item.description!!.subSequence(0, 47).toString()
            for( index in processingString.length-1 downTo 30 ) {
                if (processingString[index] == ch){
                    lastString = processingString.subSequence(0, index).toString()
                    break
                    }
                }
            val str = "$lastString..."
            textView.text = str
            } else{
            textView.text = item.description
        }
    }
}

data class Photo(
    val id: String,
    val name: String?,
    var description: String?,
    val url: String,
    val bestUrl: String
)
