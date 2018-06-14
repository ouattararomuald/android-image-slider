package com.ouattararomuald.slider

import android.net.Uri
import android.support.annotation.DrawableRes
import android.widget.ImageView
import java.io.File

/** Loads image into [ImageView]. */
interface ImageLoader {

  /** Loads an image into the given [imageView] using the specified path. */
  fun load(path: String, imageView: ImageView)

  /** Loads an image into the given [imageView] using the specified image file. */
  fun load(file: File, imageView: ImageView)

  /** Loads an image into the given [imageView] using the specified URI. */
  fun load(uri: Uri, imageView: ImageView)

  /**
   * Loads an image into the given [imageView] using the specified resource drawable resource ID.
   */
  fun load(@DrawableRes resourceId: Int, imageView: ImageView)

  /** Creates an [ImageLoader] instance. */
  abstract class Factory<T : ImageLoader> {

    /** Creates an [ImageLoader]. */
    abstract fun create(): T
  }
}
