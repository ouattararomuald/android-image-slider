---
title: ImageSlider - 
---

[com.ouattararomuald.slider](../index.html) / [ImageSlider](./index.html)

# ImageSlider

`class ImageSlider : ConstraintLayout`

Layout manager that allows auto-flip left and right through images.

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_autoRecoverAfterTouchEvent

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_initWithAutoCycling

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorBackground

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_initialSlideDelay

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorFillColor

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorPageColor

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorRadius

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorSnap

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorStrokeColor

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorStrokeWidth

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorsLeftMargin

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorsTopMargin

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorsRightMargin

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_indicatorsBottomMargin

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_sliderBackground

**Attr**
ref com.ouattararomuald.slider.R.styleable#ImageSlider_slideTransitionInterval

### Constructors

| [&lt;init&gt;](-init-.html) | `ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`)`<br>`ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?)`<br>`ImageSlider(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, attrs: `[`AttributeSet`](https://developer.android.com/reference/android/util/AttributeSet.html)`?, defStyleAttr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| [adapter](adapter.html) | `var adapter: `[`SliderAdapter`](../-slider-adapter/index.html)`?`<br>Adapter that populates this image slider. |
| [isPageIndicatorVisible](is-page-indicator-visible.html) | `var isPageIndicatorVisible: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>Returns true when the page indicator visibility is [View.VISIBLE](https://developer.android.com/reference/android/view/View.html#VISIBLE), false otherwise. |
| [pageTransformer](page-transformer.html) | `var pageTransformer: `[`PageTransformer`](https://developer.android.com/reference/androidx/androidx/viewpager/widget/ViewPager/PageTransformer.html)`?`<br>Page transformer representing the animation you want to apply to each slide transition. |

### Functions

| [onInterceptTouchEvent](on-intercept-touch-event.html) | `fun onInterceptTouchEvent(event: `[`MotionEvent`](https://developer.android.com/reference/android/view/MotionEvent.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setIndicatorsBottomMargin](set-indicators-bottom-margin.html) | `fun setIndicatorsBottomMargin(bottom: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the bottom margin in pixels under the indicators. |
| [setIndicatorsMargin](set-indicators-margin.html) | `fun setIndicatorsMargin(left: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, top: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, right: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, bottom: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the margin in pixels |
| [setOnPageChangeListener](set-on-page-change-listener.html) | `fun setOnPageChangeListener(listener: `[`OnPageChangeListener`](https://developer.android.com/reference/androidx/androidx/viewpager/widget/ViewPager/OnPageChangeListener.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets a page change listener which will receive forwarded events. |
| [startAutoLooping](start-auto-looping.html) | `fun startAutoLooping(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Starts slides auto transitions. |
| [stopAutoLooping](stop-auto-looping.html) | `fun stopAutoLooping(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Stops slides auto transitions. |

