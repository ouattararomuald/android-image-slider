package com.ouattararomuald.slider

import android.content.Context
import android.content.res.TypedArray
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView

class ImageSlider : ConstraintLayout {

  private lateinit var viewPager: ViewPager
  private lateinit var indicator: TabLayout

  private val emptyCallback = object : Callback {
    override fun loadImageFor(imageView: ImageView, position: Int) {
    }
  }

  private var indicatorSetupWithPager = false

  var adapter: SliderAdapter? = null
    set(value) {
      if (value != null) {
        field = value
        viewPager.adapter = field

        setupIndicatorWithViewPagerIfNecessary()
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
  }

  private fun setupIndicatorWithViewPagerIfNecessary() {
    if (!indicatorSetupWithPager) {
      indicator.setupWithViewPager(viewPager, true)
      indicatorSetupWithPager = true
    }
  }
}