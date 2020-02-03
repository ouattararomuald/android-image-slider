package com.ouattararomuald.slider

import android.widget.ImageView

/** Loads image into [ImageView]. */
interface ImageLoader {

  /** Loads an image into the given [imageView] using the specified path. */
  fun load(path: String, imageView: ImageView)

  /** Creates an [ImageLoader] instance. */
  interface Factory<T : ImageLoader> {

    /** Creates an [ImageLoader]. */
    fun create(): T
  }
}
