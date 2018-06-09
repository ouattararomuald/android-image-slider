package com.ouattararomuald.slider

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.os.Handler
import android.support.annotation.IntDef
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v7.content.res.AppCompatResources
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import java.util.Timer
import java.util.TimerTask

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
  private val indicator: TabLayout

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

  private var timer: Timer? = null
  private var sliderTimer: SliderTimer? = null

  private val viewPagerTouchLister = OnTouchListener { _, event ->
    when (event.action) {
      MotionEvent.ACTION_UP -> {
        if (autoRecoverAfterTouchEvent) {
          startAutoCycling()
        }
      }
    }

    return@OnTouchListener false
  }

  /** Page transformer representing the animation you want to apply to each slide transition. */
  var pageTransformer: ViewPager.PageTransformer? = null
    set(value) {
      field = value
      if (value != null) {
        viewPager.setPageTransformer(true, value)
      }
    }

  /** Adapter that populates this image slider. */
  var adapter: SliderAdapter? = null
    set(value) {
      field = value
      stopAutoCycling()

      if (value != null) {
        viewPager.adapter = field

        setupIndicatorWithViewPagerIfNecessary()

        if (initWithAutoCycling) {
          startAutoCycling()
        }
      }
    }

  constructor(context: Context) : this(context, null)

  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  @SuppressLint("ClickableViewAccessibility")
  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
      : super(context, attrs, defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.slider, this, true)

    viewPager = findViewById(R.id.view_pager)
    indicator = findViewById(R.id.indicator)

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
    }

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

  override fun onInterceptTouchEvent(event: MotionEvent): Boolean {
    if (!autoRecoverAfterTouchEvent) {
      return false
    }

    when (event.action) {
      MotionEvent.ACTION_DOWN -> stopAutoCycling()
    }

    return false
  }

  private fun setupIndicatorWithViewPagerIfNecessary() {
    if (!indicatorSetupWithPager) {
      indicator.setupWithViewPager(viewPager, true)
      indicatorSetupWithPager = true
    }
  }

  /**
   * Sets the visibility of the indicator page.
   *
   * @param visibility One of [View.VISIBLE], [View.INVISIBLE] or [View.GONE]
   */
  fun setPageIndicatorVisibility(visibility: Int) {
    indicator.visibility = visibility
  }

  /** Starts slides auto transitions. */
  fun startAutoCycling() {
    if (!isAutoCycling) {
      timer?.cancel()
      sliderTimer?.cancel()

      timer = Timer()
      sliderTimer = SliderTimer()
      timer?.scheduleAtFixedRate(sliderTimer, initialSlideDelay, slideTransitionInterval)
      isAutoCycling = true
    }
  }

  /** Pauses slides auto transitions. */
  fun stopAutoCycling() {
    if (isAutoCycling) {
      timer?.cancel()
      sliderTimer?.cancel()
      isAutoCycling = false
    }
  }

  fun addOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.addOnPageChangeListener(pageChangeListener)
  }

  fun removeOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.removeOnPageChangeListener(pageChangeListener)
  }

  private inner class SliderTimer : TimerTask() {
    override fun run() {
      adapter?.let {
        val mainHandler = Handler(context.mainLooper)
        mainHandler.post {
          if (viewPager.currentItem < it.slideNumbers - 1) {
            viewPager.currentItem = viewPager.currentItem + 1
          } else {
            viewPager.currentItem = 0
          }
        }
      }
    }
  }
}