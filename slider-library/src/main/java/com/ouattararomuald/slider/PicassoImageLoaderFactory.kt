package com.ouattararomuald.slider

import androidx.annotation.DrawableRes
import android.widget.ImageView

/**
 * An [ImageLoader.Factory] which uses Picasso.
 *
 * @property errorResId an error drawable to be used if the request image could not be loaded.
 * @property placeholderResId a placeholder drawable to be used while the image is being loaded.
 * If the requested image is not immediately available in the memory cache then this resource will
 * be set on the target [ImageView].
 */
class PicassoImageLoaderFactory(
  @DrawableRes private val errorResId: Int = 0,
  @DrawableRes private val placeholderResId: Int = 0
) : ImageLoader.Factory<PicassoImageLoader>() {

  override fun create(): PicassoImageLoader = PicassoImageLoader(errorResId, placeholderResId)
}
