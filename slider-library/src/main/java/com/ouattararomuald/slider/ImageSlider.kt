package com.ouattararomuald.slider

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.support.v7.content.res.AppCompatResources
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.animation.Animation
import android.widget.LinearLayout

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
  private val descriptionLayout: LinearLayout
  private val descriptionTextView: AppCompatTextView

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

  private var onPageChangeListener = object : ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
      adapter?.let {
        displayDescriptionIfAvailable(it)
      }
    }
  }

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

        displayDescriptionIfAvailable(field!!)
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
  var pageIndicatorVisibility: Int = View.VISIBLE
    set(value) {
      field = value
      indicator.visibility = field
    }

  /**
   * [Animation] used to animate the entrance of the description text.
   *
   * Note that the exit animation will be played automatically at the end of the entrance animation.
   * To avoid this set the [descriptionExitAnimation]  to `null`.
   *
   * @see [descriptionExitAnimation]
   */
  private var descriptionEntranceAnimation: Animation? = null
    set(value) {
      field = value
      field?.setAnimationListener(descriptionAnimationListener)
    }

  /**
   * [Animation] used to animate the exit of the description text.
   *
   * @see [descriptionEntranceAnimation]
   */
  private var descriptionExitAnimation: Animation? = null

  private var descriptionAnimationListener = object : Animation.AnimationListener {
    override fun onAnimationRepeat(animation: Animation) {
    }

    override fun onAnimationEnd(animation: Animation) {
      descriptionExitAnimation?.let { startDescriptionExitAnimation() }
    }

    override fun onAnimationStart(animation: Animation) {
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
    descriptionLayout = findViewById(R.id.description_layout)
    descriptionTextView = findViewById(R.id.description_textview)

    descriptionEntranceAnimation = AnimationGenerator.generateEntranceAnimation(
        descriptionLayout, AnimationType.TRANSLATION
    )

    viewPager.addOnPageChangeListener(onPageChangeListener)
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
      indicator.setupWithViewPager(viewPager, true)
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

  fun addOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.addOnPageChangeListener(pageChangeListener)
  }

  fun removeOnPageChangeListener(pageChangeListener: ViewPager.OnPageChangeListener) {
    viewPager.removeOnPageChangeListener(pageChangeListener)
  }

  private fun displayDescriptionIfAvailable(sliderAdapter: SliderAdapter) {
    if (sliderAdapter.hasDescriptions) {
      startDescriptionEntranceAnimation()
      descriptionTextView.text = sliderAdapter.descriptions[viewPager.currentItem]
    }
  }

  /** Animates the entrance of the description text. */
  private fun startDescriptionEntranceAnimation() {
    descriptionEntranceAnimation = AnimationGenerator.generateEntranceAnimation(
        descriptionLayout, AnimationType.TRANSLATION
    )
    descriptionEntranceAnimation?.setAnimationListener(descriptionAnimationListener)
    descriptionLayout.startAnimation(descriptionEntranceAnimation)
  }

  /** Animates the exit of the description text. */
  private fun startDescriptionExitAnimation() {
    descriptionExitAnimation = AnimationGenerator.generateExitAnimation(
        descriptionLayout, AnimationType.TRANSLATION
    )
    descriptionLayout.startAnimation(descriptionExitAnimation)
  }
}
