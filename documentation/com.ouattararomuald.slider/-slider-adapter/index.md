---
title: SliderAdapter - 
---

[com.ouattararomuald.slider](../index.html) / [SliderAdapter](./index.html)

# SliderAdapter

`class SliderAdapter : `[`PagerAdapter`](https://developer.android.com/reference/androidx/androidx/viewpager/widget/PagerAdapter.html)

Adapter to create a slider.

The slider will have the given number of slides.

### Types

| [ImageViewClickListener](-image-view-click-listener/index.html) | `interface ImageViewClickListener` |

### Constructors

| [&lt;init&gt;](-init-.html) | `SliderAdapter(context: `[`Context`](https://developer.android.com/reference/android/content/Context.html)`, imageLoaderFactory: `[`ImageLoader.Factory`](../-image-loader/-factory/index.html)`<*>, imageUrls: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>, descriptions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`> = emptyList(), sliderId: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null)`<br>Adapter to create a slider. |

### Properties

| [descriptions](descriptions.html) | `val descriptions: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>images descriptions. |
| [id](id.html) | `val id: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ID of the slider. |
| [imageUrls](image-urls.html) | `val imageUrls: `[`List`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-list/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>`<br>urls of images to display. |

### Functions

| [destroyItem](destroy-item.html) | `fun destroyItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, obj: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getCount](get-count.html) | `fun getCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [instantiateItem](instantiate-item.html) | `fun instantiateItem(container: `[`ViewGroup`](https://developer.android.com/reference/android/view/ViewGroup.html)`, position: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html) |
| [isViewFromObject](is-view-from-object.html) | `fun isViewFromObject(view: `[`View`](https://developer.android.com/reference/android/view/View.html)`, obj: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [setImageClickListener](set-image-click-listener.html) | `fun setImageClickListener(listener: `[`SliderAdapter.ImageViewClickListener`](-image-view-click-listener/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

