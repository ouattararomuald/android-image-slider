---
title: ImageLoader - 
---

[com.ouattararomuald.slider](../index.html) / [ImageLoader](./index.html)

# ImageLoader

`abstract class ImageLoader`

Loads image into [ImageView](https://developer.android.com/reference/android/widget/ImageView.html).

### Types

| [EventListener](-event-listener/index.html) | `interface EventListener` |
| [Factory](-factory/index.html) | `interface Factory<T : `[`ImageLoader`](./index.html)`>`<br>Creates an [ImageLoader](./index.html) instance. |

### Constructors

| [&lt;init&gt;](-init-.html) | `ImageLoader(eventListener: `[`ImageLoader.EventListener`](-event-listener/index.html)`? = null)`<br>Loads image into [ImageView](https://developer.android.com/reference/android/widget/ImageView.html). |

### Functions

| [load](load.html) | `abstract fun load(path: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, imageView: `[`ImageView`](https://developer.android.com/reference/android/widget/ImageView.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Loads an image into the given [imageView](load.html#com.ouattararomuald.slider.ImageLoader$load(kotlin.String, android.widget.ImageView)/imageView) using the specified path. |

