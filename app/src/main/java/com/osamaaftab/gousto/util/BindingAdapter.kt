package com.osamaaftab.gousto.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import coil.transform.CircleCropTransformation

@BindingAdapter("url")
fun ImageView.setImageUrl(value: String) {

    this.load(value) {
        crossfade(true)
        diskCachePolicy(CachePolicy.ENABLED)
        memoryCachePolicy(CachePolicy.ENABLED)
        transformations(CircleCropTransformation())

    }
}











