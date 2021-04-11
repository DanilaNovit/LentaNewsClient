package com.novitsky.lentanewsclient.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

class UrlImageView(
    context: Context,
    attrs: AttributeSet
): AppCompatImageView(context, attrs) {
    fun uploadImage(url: String) {
        Glide
            .with(context)
            .load(url)
            .into(this)
    }
}