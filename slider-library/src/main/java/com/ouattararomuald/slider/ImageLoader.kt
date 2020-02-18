package com.ouattararomuald.slider

import android.widget.ImageView

/** Loads image into [ImageView]. */
abstract class ImageLoader(private val eventListener: EventListener? = null) {

  /** Configures the [imageView] at the given position. */
  internal fun configureImageView(imageView: ImageView) {
    eventListener?.onImageViewConfiguration(imageView)
  }

  /** Loads an image into the given [imageView] using the specified path. */
  abstract fun load(path: String, imageView: ImageView)

  interface EventListener {
    /**
     * Called before the image is loaded in the [imageView].
     *
     * You can use this method to customize the image view.
     *
     * @param imageView [ImageView] that will be used to display the image.
     */
    fun onImageViewConfiguration(imageView: ImageView)
  }

  /** Creates an [ImageLoader] instance. */
  interface Factory<T : ImageLoader> {

    /** Creates an [ImageLoader]. */
    fun create(): T
  }
}
