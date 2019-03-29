package com.ouattararomuald.slider

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import androidx.appcompat.content.res.AppCompatResources
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.ViewCompat
import androidx.core.view.isVisible
import androidx.viewpager.widget.ViewPager
import com.ouattararomuald.slider.indicator.CirclePageIndicator

/**
 * Layout manager that allows auto-flip left and right through images.
 *
 *
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorBackground
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_initialSlideDelay
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_sliderBackground
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_slideTransitionInterval
 */
class ImageSlider : ConstraintLayout {

  companion object {
    private const val DEFAULT_DELAY = 3000L
    private const val DEFAULT_PERIOD = 5000L
  }

  private val viewPager: ViewPager
  private val indicator: CirclePageIndicator

  private var sliderBackgroundResId: Int = 0
  private var indicatorBackgroundResId: Int = 0
  private var initialSlideDelay: Long = DEFAULT_DELAY
  private var slideTransitionInterval: Long = DEFAULT_PERIOD

  private var indicatorSetupWithPager = false

  /** Determines whether or not this slider is currently switching between slides automatically. */
  private var isAutoCycling = false

  /**
   * Determines whether or not this slider should immediately starts its transitions after
   * its adapter is assigned.
   */
  private var initWithAutoCycling = true

  /** Determines whether or not the ImageSlider should recover after user touch event. */
  private var autoRecoverAfterTouchEvent = true

  private val loopHandler: LoopHandler

  private val viewPagerTouchLister = OnTouchListener { _, event ->
    when (event.action) {
      MotionEvent.ACTION_UP -> {
        if (autoRecoverAfterTouchEvent) {
          startAutoLooping()
        }
      }
    }

    return@OnTouchListener false
  }

  /** Page transformer representing the animation you want to apply to each slide transition. */
  var pageTransformer: ViewPager.PageTransformer? = null
    set(value) {
      field = value
      if (field != null) {
        viewPager.setPageTransformer(true, field)
      }
    }

  /** Adapter that populates this image slider. */
  var adapter: SliderAdapter? = null
    set(value) {
      field = value
      stopAutoLooping()

      if (value != null) {
        viewPager.adapter = field

        setupIndicatorWithViewPagerIfNecessary()

        if (initWithAutoCycling) {
          startAutoLooping()
        }
      }
    }

  /**
   * Sets the visibility of the page indicator.
   * The value must be one of [View.VISIBLE], [View.INVISIBLE] or [View.GONE]
   */
  @Deprecated(
      message = "Use isPageIndicatorVisible instead",
      replaceWith = ReplaceWith("isPageIndicatorVisible"),
      level = DeprecationLevel.WARNING
  )
  var pageIndicatorVisibility: Int = View.VISIBLE
    set(value) {
      field = value
      indicator.visibility = field
    }

  /**
   * Returns true when the page indicator visibility is [View.VISIBLE], false otherwise.
   *
   * ```
   * if (view.isVisible) {
   *     // Behavior...
   * }
   * ```
   *
   * Setting this property to true sets the visibility to [View.VISIBLE], false to [View.GONE].
   *
   * ```
   * view.isVisible = true
   * ```
   */
  var isPageIndicatorVisible: Boolean = true
    get() = indicator.isVisible
    set(value) {
      field = value
      indicator.isVisible = value
    }

  constructor(context: Context) : this(context, null)

  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  @SuppressLint("ClickableViewAccessibility")
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
      : super(context, attrs, defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.slider, this, true)

    viewPager = findViewById(R.id.view_pager)
    indicator = findViewById(R.id.pageIndicator)

    viewPager.setOnTouchListener(viewPagerTouchLister)

    val attributes: TypedArray = context.theme.obtainStyledAttributes(
        attrs, R.styleable.ImageSlider, defStyleAttr, 0
    )

    attributes.apply {
      autoRecoverAfterTouchEvent = getBoolean(
          R.styleable.ImageSlider_autoRecoverAfterTouchEvent, true
      )
      initWithAutoCycling = getBoolean(R.styleable.ImageSlider_initWithAutoCycling, true)
      indicatorBackgroundResId = getResourceId(
          R.styleable.ImageSlider_indicatorBackground, 0
      )
      sliderBackgroundResId = getResourceId(R.styleable.ImageSlider_sliderBackground, 0)
      initialSlideDelay = getInt(
          R.styleable.ImageSlider_initialSlideDelay, DEFAULT_DELAY.toInt()
      ).toLong()
      slideTransitionInterval = getInt(
          R.styleable.ImageSlider_slideTransitionInterval, DEFAULT_PERIOD.toInt()
      ).toLong()

      configureIndicator(this)
    }

    loopHandler = LoopHandler(initialSlideDelay, slideTransitionInterval, onLoop = {
      adapter?.let {
        if (viewPager.currentItem < it.imageUrls.size - 1) {
          viewPager.currentItem = viewPager.currentItem + 1
        } else {
          viewPager.currentItem = 0
        }
      }
    })

    if (indicatorBackgroundResId != 0) {
      ViewCompat.setBackground(
          indicator, AppCompatResources.getDrawable(context, indicatorBackgroundResId)
      )
    }

    if (sliderBackgroundResId != 0) {
      ViewCompat.setBackground(
          this, AppCompatResources.getDrawable(context, sliderBackgroundResId)
      )
      ViewCompat.setBackground(
          viewPager, AppCompatResources.getDrawable(context, sliderBackgroundResId)
      )
    }
  }

  private fun configureIndicator(attributes: TypedArray) {
    val res = resources
    val defaultPageColor = res.getColor(R.color.default_circle_indicator_page_color)
    val defaultFillColor = res.getColor(R.color.default_circle_indicator_fill_color)
    val defaultStrokeColor = res.getColor(R.color.default_circle_indicator_stroke_color)
    val defaultStrokeWidth = res.getDimension(R.dimen.default_circle_indicator_stroke_width)
    val defaultRadius = res.getDimension(R.dimen.default_circle_indicator_radius)
    val defaultSnap = res.getBoolean(R.bool.default_circle_indicator_snap)

    attributes.apply {
      indicator.setFillColor(getColor(R.styleable.ImageSlider_indicatorFillColor, defaultFillColor))
      indicator.setPageColor(getColor(R.styleable.ImageSlider_indicatorPageColor, defaultPageColor))
      indicator.setRadius(getDimension(R.styleable.ImageSlider_indicatorRadius, defaultRadius))
      indicator.setSnap(getBoolean(R.styleable.ImageSlider_indicatorSnap, defaultSnap))
      indicator.setStrokeColor(
          getColor(R.styleable.ImageSlider_indicatorStrokeColor, defaultStrokeColor))
      indicator.setStrokeWidth(
          getDimension(R.styleable.ImageSlider_indicatorStrokeWidth, defaultStrokeWidth))
    }
  }

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    if (!autoRecoverAfterTouchEvent) {
      return false
    }

    when (event.action) {
      MotionEvent.ACTION_DOWN -> stopAutoLooping()
    }

    return false
  }

  private fun setupIndicatorWithViewPagerIfNecessary() {
    if (!indicatorSetupWithPager) {
      indicator.setViewPager(viewPager)
      indicatorSetupWithPager = true
    }
  }

  /** Starts slides auto transitions. */
  fun startAutoLooping() {
    if (!isAutoCycling) {
      loopHandler.startAutoLooping()
      isAutoCycling = true
    }
  }

  /** Stops slides auto transitions. */
  fun stopAutoLooping() {
    if (isAutoCycling) {
      loopHandler.stopAutoLooping()
      isAutoCycling = false
    }
  }

  /**
   * Set a page change listener which will receive forwarded events.
   *
   * @param listener
   */
  fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
    indicator.setOnPageChangeListener(listener)
  }

  /**
   * Add a listener that will be invoked whenever the page changes or is incrementally scrolled.
   * See [ViewPager.OnPageChangeListener].
   *
   * Components that add a listener should take care to remove it when finished. Other components
   * that take ownership of a view may call [clearOnPageChangeListeners] to remove all
   * attached listeners.
   *
   * @param pageChangeListener Listener to add.
   */
  @Deprecated(
      message = "Use setOnPageChangeListener instead",
      replaceWith = ReplaceWith("setOnPageChangeListener"),
      level = DeprecationLevel.WARNING
  )
  fun addOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.addOnPageChangeListener(pageChangeListener)
  }

  /**
   * Remove a listener that was previously added via [addOnPageChangeListener].
   *
   * @param pageChangeListener Listener to remove.
   */
  @Deprecated(
      message = "To be removed",
      level = DeprecationLevel.WARNING
  )
  fun removeOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.removeOnPageChangeListener(pageChangeListener)
  }

  /** Remove all listeners that are notified of any changes in scroll state or position. */
  @Deprecated(
      message = "To be removed",
      level = DeprecationLevel.WARNING
  )
  fun clearOnPageChangeListeners() {
    viewPager.clearOnPageChangeListeners()
  }
}
