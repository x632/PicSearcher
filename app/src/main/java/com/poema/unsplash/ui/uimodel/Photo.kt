package com.poema.unsplash.ui.uimodel

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.poema.unsplash.R
import com.poema.unsplash.other.Constants.CHARS_BEFORE_CUTTING
import com.poema.unsplash.other.Constants.WHERE_TO_STOP_LOOKING_FOR_A_SPACE

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
        if (item.description!!.length > CHARS_BEFORE_CUTTING
        ) {
            processingString  = item.description!!.subSequence(0, CHARS_BEFORE_CUTTING-1).toString()
            for( index in processingString.length-1 downTo WHERE_TO_STOP_LOOKING_FOR_A_SPACE ) {
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
    val bestUrl: String,
    val downloadLink: String,
    val downloadHtml : String
)
