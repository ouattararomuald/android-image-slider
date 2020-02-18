package com.ouattararomuald.slider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.isVisible
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager

/**
 * Adapter to create a slider.
 *
 * The slider will have the given number of slides.
 *
 * @property context Context.
 * @property imageLoaderFactory image loader factory.
 * @property imageUrls urls of images to display.
 * @property descriptions images descriptions.
 */
class SliderAdapter(
  private val context: Context,
  private val imageLoaderFactory: ImageLoader.Factory<*>,
  val imageUrls: List<String>,
  val descriptions: List<String> = emptyList()
) : PagerAdapter() {

  private val imageLoader: ImageLoader

  private lateinit var slideImageView: ImageView
  private lateinit var descriptionLayout: LinearLayout
  private lateinit var descriptionTextView: AppCompatTextView

  init {
    if (imageUrls.isEmpty()) {
      throw IllegalArgumentException("imagesUrls.size < 0")
    }
    if (descriptions.isNotEmpty() && descriptions.size != imageUrls.size) {
      throw IllegalArgumentException("Descriptions.size != imagesUrls.size")
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

  override fun getCount(): Int = imageUrls.size

  override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

  override fun instantiateItem(container: ViewGroup, position: Int): Any {
    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    val view = inflater.inflate(R.layout.slide_item_view, container, false)

    view.apply {
      slideImageView = findViewById(R.id.image)
      descriptionLayout = findViewById(R.id.description_layout)
      descriptionTextView = findViewById(R.id.description_textview)
    }

    imageLoader.configureImageView(slideImageView)
    imageLoader.load(imageUrls[position], slideImageView)

    if (descriptions.isNotEmpty()) {
      descriptionTextView.text = descriptions[position]
    }

    descriptionLayout.isVisible = descriptions.isNotEmpty()

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
