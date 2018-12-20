package com.slider.demo

import androidx.viewpager.widget.ViewPager
import com.ouattararomuald.slider.ImageLoader
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.glide.GlideImageLoaderFactory
import com.ouattararomuald.slider.loaders.picasso.PicassoImageLoaderFactory
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.slider_item.slider

internal class SliderItem(
  private val imageLoaderType: ImageLoaderType,
  private val imageUrls: Array<String>,
  private val pageTransformer: ViewPager.PageTransformer
) : Item() {

  private var imageSlider: ImageSlider? = null

  override fun bind(viewHolder: ViewHolder, position: Int) {
    imageSlider = viewHolder.slider
    viewHolder.slider.adapter = SliderAdapter(viewHolder.slider.context,
        getImageLoader(),
        imageUrls = imageUrls.toList(),
        descriptions = Data.generateDescriptions(imageLoaderType.name, imageUrls.size)
    )
    viewHolder.slider.pageTransformer = pageTransformer
  }

  override fun getLayout(): Int = R.layout.slider_item

  fun showPageIndicator() {
    imageSlider?.isPageIndicatorVisible = true
  }

  fun hidePageIndicator() {
    imageSlider?.isPageIndicatorVisible = false
  }

  private fun getImageLoader(): ImageLoader.Factory<out ImageLoader> {
    return when (imageLoaderType) {
      ImageLoaderType.GLIDE -> GlideImageLoaderFactory(
          errorResId = R.drawable.ic_error,
          placeholderResId = R.drawable.ic_placeholder
      )
      ImageLoaderType.PICASSO -> PicassoImageLoaderFactory(
          errorResId = R.drawable.ic_error,
          placeholderResId = R.drawable.ic_placeholder
      )
    }
  }
}
