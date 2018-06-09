package com.slider.demo

import android.widget.ImageView
import com.ouattararomuald.slider.ImageLoaderCallback
import com.ouattararomuald.slider.SliderAdapter
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.slider_item.slider
import miaoyongjun.pagetransformer.MagicTransformer
import miaoyongjun.pagetransformer.TransitionEffect

internal class SliderItem(private val imageUrls: Array<String>) : Item() {

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.slider.adapter = SliderAdapter(
        viewHolder.slider.context, imageUrls.size, object : ImageLoaderCallback {
      override fun loadImageFor(imageView: ImageView, position: Int) {
        Picasso.get().load(imageUrls[position]).fit().into(imageView)
      }
    })

    viewHolder.slider.pageTransformer = MagicTransformer.getPageTransformer(
        TransitionEffect.Cube
    )
  }

  override fun getLayout(): Int = R.layout.slider_item
}