package com.ouattararomuald.slider.loaders.glide

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import com.ouattararomuald.slider.ImageLoader

/**
 * Loads images image using Glide.
 *
 */
class GlideImageLoader(
  private val requestOptions: RequestOptions?,
  eventListener: EventListener? = null
) : ImageLoader(eventListener) {

  override fun load(path: String, imageView: ImageView) {
    if (requestOptions != null) {
      GlideApp.with(imageView).load(path).apply(requestOptions).into(imageView)
    } else {
      GlideApp.with(imageView).load(path).apply {
        centerCrop()
        into(imageView)
      }
    }
  }
}
