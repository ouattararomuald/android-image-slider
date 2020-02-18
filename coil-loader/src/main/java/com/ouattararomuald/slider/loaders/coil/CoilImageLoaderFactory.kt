package com.ouattararomuald.slider.loaders.coil

import coil.request.LoadRequestBuilder
import com.ouattararomuald.slider.ImageLoader

/**
 * An [ImageLoader.Factory] which uses Coil.
 *
 * @property builder options to customize loads with Coil.
 */
class CoilImageLoaderFactory(
  private val eventListener: ImageLoader.EventListener? = null,
  private val builder: LoadRequestBuilder.() -> Unit = {}
) : ImageLoader.Factory<CoilImageLoader> {

  override fun create(): CoilImageLoader = CoilImageLoader(builder, eventListener)
}