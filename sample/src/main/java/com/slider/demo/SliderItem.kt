package com.slider.demo

import android.widget.ImageView
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import coil.size.OriginalSize
import coil.size.Scale
import coil.size.Size
import coil.size.SizeResolver
import coil.transform.CircleCropTransformation
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ouattararomuald.slider.ImageLoader
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.ouattararomuald.slider.loaders.coil.CoilImageLoaderFactory
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

  private val imageLoaderEventCallback = object : ImageLoader.EventListener {
    override fun onImageViewConfiguration(imageView: ImageView) {
      imageView.scaleType = ImageView.ScaleType.FIT_XY
    }
  }

  override fun bind(viewHolder: ViewHolder, position: Int) {
    imageSlider = viewHolder.slider
    viewHolder.slider.adapter = SliderAdapter(viewHolder.slider.context,
        getImageLoader(),
        imageUrls = imageUrls.toList(),
        descriptions = Data.generateDescriptions(imageLoaderType.name, imageUrls.size)
    )
    (viewHolder.slider.adapter as SliderAdapter).setImageClickListener(object : SliderAdapter.ImageViewClickListener {
      override fun onItemClicked(sliderId: String, position: Int, imageUrl: String) {
        Toast.makeText(viewHolder.containerView.context,
                "ID: $sliderId, Position: $position, Image URL: $imageUrl", Toast.LENGTH_LONG)
                .show()
      }
    })
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
      ImageLoaderType.COIL -> {
        CoilImageLoaderFactory(imageLoaderEventCallback)
      }
      ImageLoaderType.GLIDE -> {
        val requestOptions = RequestOptions.errorOf(R.drawable.ic_error)
            .placeholder(R.drawable.ic_placeholder)
        GlideImageLoaderFactory(requestOptions = requestOptions)
      }
      ImageLoaderType.PICASSO -> PicassoImageLoaderFactory(
          errorResId = R.drawable.ic_error,
          placeholderResId = R.drawable.ic_placeholder
      )
    }
  }
}
