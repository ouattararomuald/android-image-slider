package com.ouattararomuald.slider

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Adapter to create a slider.
 *
 * The slider will have the given number of slides.
 */
class SliderAdapter(
  private val context: Context,
  /** Number of slides to display. */
  val slideNumbers: Int,
  /** Image loader callback. */
  private val imageLoaderCallback: ImageLoaderCallback,
  /** Slides descriptions. */
  val descriptions: List<String> = emptyList()
) : PagerAdapter() {

  init {
    if (slideNumbers <= 0) {
      throw IllegalArgumentException("Slides number must be greater than 0")
    }
    if (descriptions.isNotEmpty() && descriptions.size != slideNumbers) {
      throw IllegalArgumentException("Descriptions number must be the same as number of slides")
    }
  }

  /**
   * Determines whether this adapter has attached description or not.
   *
   * @return true if it has descriptions. Otherwise returns false.
   */
  val hasDescriptions: Boolean
    get() = descriptions.isNotEmpty()

  override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

  override fun getCount(): Int = slideNumbers

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.slider_view, null)

    val slideImageView = view.findViewById(R.id.image) as ImageView

    imageLoaderCallback.loadImageFor(slideImageView, position)

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
