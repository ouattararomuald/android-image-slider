package com.ouattararomuald.slider

import android.content.Context
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

/**
 * Adapter to create a slider.
 *
 * The slider will have the given number of slides.
 *
 * @param imageLoaderFactory Image loader factory.
 * @param imageUrls Urls of images to display.
 * @param descriptions Images descriptions.
 */
class SliderAdapter(
  private val context: Context,
  imageLoaderFactory: ImageLoader.Factory<*>,
  val imageUrls: List<String>,
  val descriptions: List<String> = emptyList()
) : androidx.viewpager.widget.PagerAdapter() {

  private val imageLoader: ImageLoader

  init {
    if (imageUrls.isEmpty()) {
      throw IllegalArgumentException("imagesUrls number must be greater than 0")
    }
    if (descriptions.isNotEmpty() && descriptions.size != imageUrls.size) {
      throw IllegalArgumentException("Descriptions number must be the same as number of slides")
    }
    imageLoader = imageLoaderFactory.create()
  }

  /**
   * Determines whether this adapter has attached description or not.
   *
   * @return true if it has descriptions. Otherwise returns false.
   */
  internal val hasDescriptions: Boolean
    get() = descriptions.isNotEmpty()

  override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

  override fun getCount(): Int = imageUrls.size

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.slider_view, container, false)

    val slideImageView = view.findViewById(R.id.image) as ImageView
    imageLoader.load(imageUrls[position], slideImageView)

    val viewPager = container as androidx.viewpager.widget.ViewPager
    viewPager.addView(view, 0)

    return view
  }

  override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
    val viewPager = container as androidx.viewpager.widget.ViewPager
    val view = obj as View
    viewPager.removeView(view)
  }
}
