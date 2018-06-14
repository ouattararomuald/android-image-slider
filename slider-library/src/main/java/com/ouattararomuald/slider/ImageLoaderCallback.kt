package com.ouattararomuald.slider

import android.widget.ImageView

interface ImageLoaderCallback {

  /** Loads image for the [imageView] at the given [position]. */
  fun loadImageFor(imageView: ImageView, position: Int)
}
