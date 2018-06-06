@file:JvmName("SliderAdapter")

package com.ouattararomuald.slider

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.widget.ImageView

class SliderAdapter(
  private val context: Context,
  /** Number of slides to display. */
  private val slideNumbers: Int,
  private val callback: Callback
) : PagerAdapter() {

  override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

  override fun getCount(): Int = slideNumbers

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.slider_item, null)

    val slideImageView = view.findViewById(R.id.slide_image) as ImageView

    callback.loadImageFor(slideImageView, position)

    val viewPager = container as ViewPager
    viewPager.addView(view, 0)

    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
    val viewPager = container as ViewPager
    val view = obj as View
    viewPager.removeView(view)
  }
}