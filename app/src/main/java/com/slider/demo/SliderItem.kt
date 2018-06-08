package com.slider.demo

import android.support.v4.view.ViewPager
import android.widget.ImageView
import com.ouattararomuald.slider.ImageLoaderCallback
import com.ouattararomuald.slider.SliderAdapter
import com.squareup.picasso.Picasso
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.slider_item.slider

internal class SliderItem(private val imageUrls: Array<String>) : Item() {

  override fun bind(viewHolder: ViewHolder, position: Int) {
    viewHolder.slider.adapter = SliderAdapter(
        viewHolder.slider.context, imageUrls.size, object : ImageLoaderCallback {
      override fun loadImageFor(imageView: ImageView, position: Int) {
        Picasso.get().load(imageUrls[position]).fit().into(imageView)
      }
    })

    viewHolder.slider.pagerTransformer = ViewPager.PageTransformer { view, pos->
      val pageWidth = view.width

      if (pos < -1) { // [-Infinity,-1)
        // This page is way off-screen to the left.
        view.alpha = 1F
      } else if (pos <= 1) { // [-1,1]

        view.translationX = -pos * (pageWidth / 2) //Half the normal speed
      } else { // (1,+Infinity]
        // This page is way off-screen to the right.
        view.alpha = 1F
      }
    }
  }

  override fun getLayout(): Int = R.layout.slider_item
}