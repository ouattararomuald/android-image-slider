package com.ouattararomuald.slider.loaders.coil

import android.widget.ImageView
import coil.api.load
import coil.request.LoadRequestBuilder
import com.ouattararomuald.slider.ImageLoader

/**
 * Loads images image using Coil.
 *
 */
class CoilImageLoader(
  private val builder: LoadRequestBuilder.() -> Unit = {},
  eventListener: EventListener? = null
) : ImageLoader(eventListener) {

  override fun load(path: String, imageView: ImageView) {
    imageView.load(path) {
      apply(builder)
    }
  }
}
