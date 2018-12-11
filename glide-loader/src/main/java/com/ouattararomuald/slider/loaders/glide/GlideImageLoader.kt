package com.ouattararomuald.slider.loaders.glide

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.ouattararomuald.slider.ImageLoader

/**
 * Loads images image using Glide.
 *
 * @property errorResId an error drawable to be used if the request image could not be loaded.
 * @property placeholderResId a placeholder drawable to be used while the image is being loaded.
 * If the requested image is not immediately available in the memory cache then this resource
 * will be set on the target [ImageView].
 */
class GlideImageLoader(
  @DrawableRes private val errorResId: Int,
  @DrawableRes private val placeholderResId: Int
) : ImageLoader {

  override fun load(path: String, imageView: ImageView) {
    GlideApp.with(imageView).load(path).apply {
      if (placeholderResId > 0) {
        placeholder(placeholderResId)
      }
      if (errorResId > 0) {
        error(errorResId)
      }
      centerCrop()
      into(imageView)
    }
  }
}