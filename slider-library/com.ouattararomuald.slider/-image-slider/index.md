---
title: ImageSlider - slider-library
---

[slider-library](../../index.html) / [com.ouattararomuald.slider](../index.html) / [ImageSlider](./index.html)

# ImageSlider

`class ImageSlider : ConstraintLayout`

Layout manager that allows auto-flip left and right through images.

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorBackground

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_initialSlideDelay

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_sliderBackground

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_slideTransitionInterval

### Constructors

| [&lt;init&gt;](-init-.html) | `ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)`<br>`ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?)`<br>`ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| [adapter](adapter.html) | `var adapter: `[`SliderAdapter`](../-slider-adapter/index.html)`?`<br>Adapter that populates this image slider. |
| [pageIndicatorVisibility](page-indicator-visibility.html) | `var pageIndicatorVisibility: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Sets the visibility of the page indicator. The value must be one of [View.VISIBLE](https://developer.android.com/reference/android/view/View.html#VISIBLE), [View.INVISIBLE](https://developer.android.com/reference/android/view/View.html#INVISIBLE) or [View.GONE](https://developer.android.com/reference/android/view/View.html#GONE) |
| [pageTransformer](page-transformer.html) | `var pageTransformer: PageTransformer?`<br>Page transformer representing the animation you want to apply to each slide transition. |

### Functions

| [addOnPageChangeListener](add-on-page-change-listener.html) | `fun addOnPageChangeListener(pageChangeListener: OnPageChangeListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Add a listener that will be invoked whenever the page changes or is incrementally scrolled. See [ViewPager.OnPageChangeListener](#). |
| [clearOnPageChangeListeners](clear-on-page-change-listeners.html) | `fun clearOnPageChangeListeners(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove all listeners that are notified of any changes in scroll state or position. |
| [onInterceptTouchEvent](on-intercept-touch-event.html) | `fun onInterceptTouchEvent(event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [removeOnPageChangeListener](remove-on-page-change-listener.html) | `fun removeOnPageChangeListener(pageChangeListener: OnPageChangeListener): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Remove a listener that was previously added via [addOnPageChangeListener](add-on-page-change-listener.html). |
| [startAutoLooping](start-auto-looping.html) | `fun startAutoLooping(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Starts slides auto transitions. |
| [stopAutoLooping](stop-auto-looping.html) | `fun stopAutoLooping(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Stops slides auto transitions. |

