package com.ouattararomuald.slider.loaders.glide

import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.ouattararomuald.slider.ImageLoader

/**
 * An [ImageLoader.Factory] which uses Glide.
 *
 * @property errorResId an error drawable to be used if the request image could not be loaded.
 * @property placeholderResId a placeholder drawable to be used while the image is being loaded.
 * If the requested image is not immediately available in the memory cache then this resource will
 * be set on the target [ImageView].
 */
class GlideImageLoaderFactory(
  @DrawableRes private val errorResId: Int = 0,
  @DrawableRes private val placeholderResId: Int = 0
) : ImageLoader.Factory<GlideImageLoader>() {

  override fun create(): GlideImageLoader = GlideImageLoader(errorResId, placeholderResId)
}