/*
 * Copyright (C) 2011 Patrik Akerfeldt
 * Copyright (C) 2011 Jake Wharton
 * Copyright (C) 2019 Romuald Ouattara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ouattararomuald.slider.indicators

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Paint.ANTI_ALIAS_FLAG
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import android.widget.LinearLayout.HORIZONTAL
import android.widget.LinearLayout.VERTICAL
import androidx.core.content.ContextCompat
import androidx.core.view.MotionEventCompat
import androidx.viewpager.widget.ViewPager
import com.ouattararomuald.slider.R

/**
 * Draws circles (one for each view). The current view position is filled and
 * others are only stroked.
 */
@Suppress("LongMethod", "LargeClass", "ComplexMethod", "TooManyFunctions", "ReturnCount",
    "MagicNumber", "NestedBlockDepth", "MatchingDeclarationName")
internal class CirclePageIndicator : View, PageIndicator {

  companion object {
    private const val INVALID_POINTER = -1
  }

  private var radius: Float = 0f
  private val paintPageFill = Paint(ANTI_ALIAS_FLAG)
  private val paintStroke = Paint(ANTI_ALIAS_FLAG)
  private val paintFill = Paint(ANTI_ALIAS_FLAG)
  private var viewPager: ViewPager? = null
  private var listener: ViewPager.OnPageChangeListener? = null
  private var currentPage: Int = 0
  private var snapPage: Int = 0
  private var pageOffset: Float = 0f
  private var scrollState: Int = 0
  private var orientation: Int = 0
  private var centered: Boolean = false
  private var snap: Boolean = false

  private var touchSlop: Int = 0
  private var lastMotionX = -1f
  private var activePointerId = INVALID_POINTER
  private var isDragging: Boolean = false

  constructor(context: Context) : this(context, null)

  constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)

  constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs,
      defStyleAttr) {
    if (isInEditMode) return

    //Load defaults from resources
    val res = resources
    val defaultPageColor =
        ContextCompat.getColor(context, R.color.default_circle_indicator_page_color)
    val defaultFillColor =
        ContextCompat.getColor(context, R.color.default_circle_indicator_fill_color)
    val defaultOrientation = res.getInteger(R.integer.default_circle_indicator_orientation)
    val defaultStrokeColor =
        ContextCompat.getColor(context, R.color.default_circle_indicator_stroke_color)
    val defaultStrokeWidth = res.getDimension(R.dimen.default_circle_indicator_stroke_width)
    val defaultRadius = res.getDimension(R.dimen.default_circle_indicator_radius)
    val defaultCentered = res.getBoolean(R.bool.default_circle_indicator_centered)
    val defaultSnap = res.getBoolean(R.bool.default_circle_indicator_snap)

    //Retrieve styles attributes
    val a = context.obtainStyledAttributes(attrs, R.styleable.CirclePageIndicator, defStyleAttr, 0)

    centered = a.getBoolean(R.styleable.CirclePageIndicator_centered, defaultCentered)
    orientation = a.getInt(R.styleable.CirclePageIndicator_android_orientation, defaultOrientation)
    paintPageFill.style = Paint.Style.FILL
    paintPageFill.color = a.getColor(R.styleable.CirclePageIndicator_pageColor, defaultPageColor)
    paintStroke.style = Paint.Style.STROKE
    paintStroke.color = a.getColor(R.styleable.CirclePageIndicator_strokeColor, defaultStrokeColor)
    paintStroke.strokeWidth =
        a.getDimension(R.styleable.CirclePageIndicator_strokeWidth, defaultStrokeWidth)
    paintFill.style = Paint.Style.FILL
    paintFill.color = a.getColor(R.styleable.CirclePageIndicator_fillColor, defaultFillColor)
    radius = a.getDimension(R.styleable.CirclePageIndicator_radius, defaultRadius)
    snap = a.getBoolean(R.styleable.CirclePageIndicator_snap, defaultSnap)

    val background = a.getDrawable(R.styleable.CirclePageIndicator_android_background)
    if (background != null) {
      setBackground(background)
    }

    a.recycle()

    val viewConfiguration = ViewConfiguration.get(context)
    touchSlop = viewConfiguration.scaledPagingTouchSlop
  }

  fun setCentered(centered: Boolean) {
    this.centered = centered
    invalidate()
  }

  fun isCentered(): Boolean {
    return centered
  }

  fun setPageColor(pageColor: Int) {
    paintPageFill.color = pageColor
    invalidate()
  }

  fun getPageColor(): Int {
    return paintPageFill.color
  }

  fun setFillColor(fillColor: Int) {
    paintFill.color = fillColor
    invalidate()
  }

  fun getFillColor(): Int {
    return paintFill.color
  }

  fun setOrientation(orientation: Int) {
    when (orientation) {
      HORIZONTAL, VERTICAL -> {
        this.orientation = orientation
        requestLayout()
      }

      else -> throw IllegalArgumentException("Orientation must be either HORIZONTAL or VERTICAL.")
    }
  }

  fun getOrientation(): Int {
    return orientation
  }

  fun setStrokeColor(strokeColor: Int) {
    paintStroke.color = strokeColor
    invalidate()
  }

  fun getStrokeColor(): Int {
    return paintStroke.color
  }

  fun setStrokeWidth(strokeWidth: Float) {
    paintStroke.strokeWidth = strokeWidth
    invalidate()
  }

  fun getStrokeWidth(): Float {
    return paintStroke.strokeWidth
  }

  fun setRadius(radius: Float) {
    this.radius = radius
    invalidate()
  }

  fun getRadius(): Float {
    return radius
  }

  fun setSnap(snap: Boolean) {
    this.snap = snap
    invalidate()
  }

  fun isSnap(): Boolean {
    return snap
  }

  override fun onDraw(canvas: Canvas) {
    super.onDraw(canvas)

    if (viewPager == null) {
      return
    }
    val count = viewPager!!.adapter!!.count
    if (count == 0) {
      return
    }

    if (currentPage >= count) {
      setCurrentItem(count - 1)
      return
    }

    val longSize: Int
    val longPaddingBefore: Int
    val longPaddingAfter: Int
    val shortPaddingBefore: Int
    if (orientation == HORIZONTAL) {
      longSize = width
      longPaddingBefore = paddingLeft
      longPaddingAfter = paddingRight
      shortPaddingBefore = paddingTop
    } else {
      longSize = height
      longPaddingBefore = paddingTop
      longPaddingAfter = paddingBottom
      shortPaddingBefore = paddingLeft
    }

    val threeRadius = radius * 3
    val shortOffset = shortPaddingBefore + radius
    var longOffset = longPaddingBefore + radius
    if (centered) {
      longOffset += (longSize - longPaddingBefore - longPaddingAfter) / 2.0f - count * threeRadius / 2.0f
    }

    var dX: Float
    var dY: Float

    var pageFillRadius = radius
    if (paintStroke.strokeWidth > 0) {
      pageFillRadius -= paintStroke.strokeWidth / 2.0f
    }

    //Draw stroked circles
    for (iLoop in 0 until count) {
      val drawLong = longOffset + iLoop * threeRadius
      if (orientation == HORIZONTAL) {
        dX = drawLong
        dY = shortOffset
      } else {
        dX = shortOffset
        dY = drawLong
      }
      // Only paint fill if not completely transparent
      if (paintPageFill.alpha > 0) {
        canvas.drawCircle(dX, dY, pageFillRadius, paintPageFill)
      }

      // Only paint stroke if a stroke width was non-zero
      if (pageFillRadius != radius) {
        canvas.drawCircle(dX, dY, radius, paintStroke)
      }
    }

    //Draw the filled circle according to the current scroll
    var cx = (if (snap) snapPage else currentPage) * threeRadius
    if (!snap) {
      cx += pageOffset * threeRadius
    }
    if (orientation == HORIZONTAL) {
      dX = longOffset + cx
      dY = shortOffset
    } else {
      dX = shortOffset
      dY = longOffset + cx
    }
    canvas.drawCircle(dX, dY, radius, paintFill)
  }

  override fun onTouchEvent(ev: android.view.MotionEvent): Boolean {
    if (super.onTouchEvent(ev)) {
      return true
    }
    if (viewPager == null || viewPager!!.adapter!!.count == 0) {
      return false
    }

    val action = ev.action and MotionEvent.ACTION_MASK
    when (action) {
      MotionEvent.ACTION_DOWN -> {
        activePointerId = ev.getPointerId(0)
        lastMotionX = ev.x
      }

      MotionEvent.ACTION_MOVE -> {
        val x = ev.x
        val deltaX = x - lastMotionX

        if (!isDragging) {
          if (Math.abs(deltaX) > touchSlop) {
            isDragging = true
          }
        }

        if (isDragging) {
          lastMotionX = x
          if (viewPager!!.isFakeDragging || viewPager!!.beginFakeDrag()) {
            viewPager!!.fakeDragBy(deltaX)
          }
        }
      }

      MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
        if (!isDragging) {
          val count = viewPager!!.adapter!!.count
          val width = width
          val halfWidth = width / 2f
          val sixthWidth = width / 6f

          if (currentPage > 0 && ev.x < halfWidth - sixthWidth) {
            if (action != MotionEvent.ACTION_CANCEL) {
              viewPager!!.currentItem = currentPage - 1
            }
            return true
          } else if (currentPage < count - 1 && ev.x > halfWidth + sixthWidth) {
            if (action != MotionEvent.ACTION_CANCEL) {
              viewPager!!.currentItem = currentPage + 1
            }
            return true
          }
        }

        isDragging = false
        activePointerId = INVALID_POINTER
        if (viewPager!!.isFakeDragging) viewPager!!.endFakeDrag()
      }

      MotionEvent.ACTION_POINTER_DOWN -> {
        lastMotionX = ev.x
        activePointerId = ev.getPointerId(ev.actionIndex)
      }

      MotionEvent.ACTION_POINTER_UP -> {
        val pointerId = ev.getPointerId(ev.actionIndex)
        if (pointerId == activePointerId) {
          val newPointerIndex = if (ev.actionIndex == 0) 1 else 0
          activePointerId = ev.getPointerId(newPointerIndex)
        }
        lastMotionX =
            MotionEventCompat.getX(ev, ev.findPointerIndex(activePointerId))
      }
    }

    return true
  }

  override fun setViewPager(view: ViewPager) {
    if (viewPager === view) {
      return
    }
    if (viewPager != null) {
      viewPager!!.clearOnPageChangeListeners()
    }
    if (view.adapter == null) {
      throw IllegalStateException("ViewPager does not have adapter instance.")
    }
    viewPager = view
    viewPager!!.addOnPageChangeListener(this)
    invalidate()
  }

  override fun setViewPager(view: ViewPager, initialPosition: Int) {
    setViewPager(view)
    setCurrentItem(initialPosition)
  }

  override fun setCurrentItem(item: Int) {
    if (viewPager == null) {
      throw IllegalStateException("ViewPager has not been bound.")
    }
    viewPager!!.currentItem = item
    currentPage = item
    invalidate()
  }

  override fun notifyDataSetChanged() {
    invalidate()
  }

  override fun onPageScrollStateChanged(state: Int) {
    scrollState = state

    if (listener != null) {
      listener!!.onPageScrollStateChanged(state)
    }
  }

  override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    currentPage = position
    pageOffset = positionOffset
    invalidate()

    if (listener != null) {
      listener!!.onPageScrolled(position, positionOffset, positionOffsetPixels)
    }
  }

  override fun onPageSelected(position: Int) {
    if (snap || scrollState == ViewPager.SCROLL_STATE_IDLE) {
      currentPage = position
      snapPage = position
      invalidate()
    }

    if (listener != null) {
      listener!!.onPageSelected(position)
    }
  }

  override fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener) {
    this.listener = listener
  }

  /*
   * (non-Javadoc)
   *
   * @see android.view.View#onMeasure(int, int)
   */
  override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
    if (orientation == HORIZONTAL) {
      setMeasuredDimension(measureLong(widthMeasureSpec), measureShort(heightMeasureSpec))
    } else {
      setMeasuredDimension(measureShort(widthMeasureSpec), measureLong(heightMeasureSpec))
    }
  }

  /**
   * Determines the width of this view
   *
   * @param measureSpec
   * A measureSpec packed into an int
   * @return The width of the view, honoring constraints from measureSpec
   */
  private fun measureLong(measureSpec: Int): Int {
    var result: Int
    val specMode = View.MeasureSpec.getMode(measureSpec)
    val specSize = View.MeasureSpec.getSize(measureSpec)

    if (specMode == View.MeasureSpec.EXACTLY || viewPager == null) {
      //We were told how big to be
      result = specSize
    } else {
      //Calculate the width according the views count
      val count = viewPager!!.adapter!!.count
      result = (paddingLeft.toFloat() + paddingRight.toFloat()
          + count.toFloat() * 2f * radius + (count - 1) * radius + 1f).toInt()
      //Respect AT_MOST value if that was what is called for by measureSpec
      if (specMode == View.MeasureSpec.AT_MOST) {
        result = Math.min(result, specSize)
      }
    }
    return result
  }

  /**
   * Determines the height of this view
   *
   * @param measureSpec
   * A measureSpec packed into an int
   * @return The height of the view, honoring constraints from measureSpec
   */
  private fun measureShort(measureSpec: Int): Int {
    var result: Int
    val specMode = View.MeasureSpec.getMode(measureSpec)
    val specSize = View.MeasureSpec.getSize(measureSpec)

    if (specMode == View.MeasureSpec.EXACTLY) {
      //We were told how big to be
      result = specSize
    } else {
      //Measure the height
      result = (2 * radius + paddingTop.toFloat() + paddingBottom.toFloat() + 1f).toInt()
      //Respect AT_MOST value if that was what is called for by measureSpec
      if (specMode == View.MeasureSpec.AT_MOST) {
        result = Math.min(result, specSize)
      }
    }
    return result
  }

  public override fun onRestoreInstanceState(state: Parcelable) {
    val savedState = state as SavedState
    super.onRestoreInstanceState(savedState.superState)
    currentPage = savedState.currentPage
    snapPage = savedState.currentPage
    requestLayout()
  }

  public override fun onSaveInstanceState(): Parcelable? {
    val superState = super.onSaveInstanceState()
    val savedState = SavedState(superState)
    savedState.currentPage = currentPage
    return savedState
  }

  internal class SavedState : View.BaseSavedState {
    var currentPage: Int = 0

    constructor(superState: Parcelable) : super(superState)

    private constructor(parcel: Parcel) : super(parcel) {
      currentPage = parcel.readInt()
    }

    override fun writeToParcel(dest: Parcel, flags: Int) {
      super.writeToParcel(dest, flags)
      dest.writeInt(currentPage)
    }

    companion object CREATOR : Parcelable.Creator<SavedState> {
      override fun createFromParcel(parcel: Parcel): SavedState {
        return SavedState(parcel)
      }

      override fun newArray(size: Int): Array<SavedState?> {
        return arrayOfNulls(size)
      }
    }
  }
}
