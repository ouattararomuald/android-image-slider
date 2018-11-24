package com.slider.demo

import androidx.viewpager.widget.ViewPager
import com.ouattararomuald.slider.PicassoImageLoaderFactory
import com.ouattararomuald.slider.SliderAdapter
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.slider_item.slider

internal class SliderItem(
  private val imageUrls: Array<String>,
  private val pageTransformer: ViewPager.PageTransformer
) : Item() {

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.slider.adapter = SliderAdapter(viewHolder.slider.context,
        PicassoImageLoaderFactory(
            errorResId = R.drawable.ic_error,
            placeholderResId = R.drawable.ic_placeholder
        ),
        imageUrls = imageUrls.toList(),
        descriptions = Data.generateDescriptions(imageUrls.size)
    )
    viewHolder.slider.pageTransformer = pageTransformer
  }

  override fun getLayout(): Int = R.layout.slider_item
}
