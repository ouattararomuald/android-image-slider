package com.ouattararomuald.slider.loaders.glide

import com.bumptech.glide.request.RequestOptions
import com.ouattararomuald.slider.ImageLoader

/**
 * An [ImageLoader.Factory] which uses Glide.
 *
 * @property requestOptions options to customize loads with Glide.
 */
class GlideImageLoaderFactory(
  private val eventListener: ImageLoader.EventListener? = null,
  private val requestOptions: RequestOptions? = null
) : ImageLoader.Factory<GlideImageLoader> {

  override fun create(): GlideImageLoader = GlideImageLoader(requestOptions, eventListener)
}