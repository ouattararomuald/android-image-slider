package com.ouattararomuald.slider

import android.content.Context
import android.content.res.TypedArray
import android.os.Handler
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v7.content.res.AppCompatResources
import android.util.AttributeSet
import android.view.LayoutInflater
import java.util.Timer
import java.util.TimerTask

/**
 *
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorBackground
 * @attr ref com.ouattararomuald.slider.R.styleable#ImageSlider_sliderBackground
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

  private var timer: Timer? = null
  private val sliderTimer: SliderTimer by lazy { SliderTimer() }

  var pagerTransformer: ViewPager.PageTransformer? = null
    set(value) {
      field = value
      if (value != null) {
        viewPager.setPageTransformer(true, value)
      }
    }

  var adapter: SliderAdapter? = null
    set(value) {
      field = value
      timer?.cancel()

      if (value != null) {
        viewPager.adapter = field

        setupIndicatorWithViewPagerIfNecessary()

        timer = Timer()
        timer?.scheduleAtFixedRate(sliderTimer, initialSlideDelay, slideTransitionInterval)
      }
    }

  constructor(context: Context) : this(context, null)

  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
      : super(context, attrs, defStyleAttr) {
    LayoutInflater.from(context).inflate(R.layout.slider, this, true)

    viewPager = findViewById(R.id.view_pager)
    indicator = findViewById(R.id.indicator)

    val attributes: TypedArray = context.theme.obtainStyledAttributes(
        attrs, R.styleable.ImageSlider, defStyleAttr, 0
    )

    attributes.apply {
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

  private fun setupIndicatorWithViewPagerIfNecessary() {
    if (!indicatorSetupWithPager) {
      indicator.setupWithViewPager(viewPager, true)
      indicatorSetupWithPager = true
    }
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