---
title: SliderAdapter - slider-library
---

[slider-library](../../index.html) / [com.ouattararomuald.slider](../index.html) / [SliderAdapter](./index.html)

# SliderAdapter

`class SliderAdapter : PagerAdapter`

Adapter to create a slider.

The slider will have the given number of slides.

### Constructors

| [&lt;init&gt;](-init-.html) | `SliderAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, imageLoaderFactory: `[`Factory`](../-image-loader/-factory/index.html)`<*>, imageUrls: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, descriptions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = emptyList())`<br>Adapter to create a slider. |

### Properties

| [descriptions](descriptions.html) | `val descriptions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>images descriptions. |
| [imageUrls](image-urls.html) | `val imageUrls: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>urls of images to display. |

### Functions

| [destroyItem](destroy-item.html) | `fun destroyItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, obj: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getCount](get-count.html) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [instantiateItem](instantiate-item.html) | `fun instantiateItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [isViewFromObject](is-view-from-object.html) | `fun isViewFromObject(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, obj: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

