---
layout: default
---

# Introduction

Create a simple slider with the following code:

**activity_main.xml**
```xml
<com.ouattararomuald.slider.ImageSlider
    android:id="@+id/image_slider"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
/>
```

**MainActivity.kt**
```kotlin
class MainActivity : AppCompatActivity() {
  
  companion object {
    private const val SLIDE_NUMBER = 10
  }

  private lateinit var imageSlider: ImageSlider
  private val imageUrls = arrayListOf(
    "http://i.imgur.com/CqmBjo5.jpg", 
    "http://i.imgur.com/zkaAooq.jpg", 
    "http://i.imgur.com/0gqnEaY.jpg"
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageSlider = findViewById(R.id.image_slider)
    imageSlider.adapter = SliderAdapter(
      this,
      PicassoImageLoaderFactory(),
      imageUrls = imageUrls,
      descriptions = Data.generateDescriptions(imageUrls.size)
    )
  }
}
```

## Image Loader

`Slider` comes with Picasso as dependency to load images. If you want to use another library, all you need is to extends `ImageLoader#Factory` and pass your factory to `SliderAdapter.

You can take a look at the `PicassoImageLoaderFactory` implementation below.

**PicassoImageLoader**
```kotlin
class PicassoImageLoader(
  @DrawableRes private val errorResId: Int,
  @DrawableRes private val placeholderResId: Int
) : ImageLoader {

  override fun load(path: String, imageView: ImageView) {
    Picasso.get().load(path).apply {
      if (placeholderResId > 0) {
        placeholder(placeholderResId)
      }
      if (errorResId > 0) {
        error(errorResId)
      }
      fit()
      into(imageView)
    }
  }
}
```

**PicassoImageLoaderFactory**
```kotlin
class PicassoImageLoaderFactory(
  @DrawableRes private val errorResId: Int = 0,
  @DrawableRes private val placeholderResId: Int = 0
) : ImageLoader.Factory<PicassoImageLoader>() {

  override fun create(): PicassoImageLoader = PicassoImageLoader(errorResId, placeholderResId)
}
```

## Animations

A slider needs animations between each transition. To create a transition, you need is to implement `ViewPager.PageTransformer` and pass it to `ImageSlider#pageTransformer` just like in the image below:

```kotlin
class MainActivity : AppCompatActivity() {
  
  companion object {
    private const val SLIDE_NUMBER = 10
  }

  private lateinit var imageSlider: ImageSlider
  private val imageUrls = arrayListOf(
    "http://i.imgur.com/CqmBjo5.jpg", 
    "http://i.imgur.com/zkaAooq.jpg", 
    "http://i.imgur.com/0gqnEaY.jpg"
  )

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageSlider = findViewById(R.id.image_slider)
    imageSlider.adapter = SliderAdapter(
      this,
      PicassoImageLoaderFactory(),
      imageUrls = imageUrls,
      descriptions = Data.generateDescriptions(imageUrls.size)
    )
    imageSlider.pageTransformer = MyPageTransformer() // Custom Page Transformer
  }
}
```

## Available XML Attributes

| Attributes                   |  Description                |
|:---------------------------  | :---------------------------|
| **autoRecoverAfterTouchEvent**   |  ```Determines whether or not the ImageSlider should recover after user touch event.```  |
| **indicatorBackground**          |  ```Reference to a background to be applied to Slider's indicator.```   |
| **initialSlideDelay**            |  ```Delay in milliseconds before the first slide change.```      |
| **initWithAutoCycling**          |  ```Determines whether or not the ImageSlider should immediately starts its transitions.``` |
| **sliderBackground**             |  ```Reference to a background to be applied to Slider.``` |
| **slideTransitionInterval**      |  ```Time in milliseconds between successive slide changes.``` |

## Download

Download the [lastest AAR](https://search.maven.org/) or grab via Gradle:

```groovy
implementation 'com.ouattararomuald:slider:1.0.0'
```

or Maven:

```xml
<dependency>
  <groupId>com.ouattararomuald</groupId>
  <artifactId>slider</artifactId>
  <version>1.0.0</version>
</dependency>
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/).

## Contributing

Contributions you say? Yes please!

**Bug report?**

If at all possible, please attach a minimal sample project or code which reproduces the bug. Screenshots are also a huge help if the problem is visual.

**Send a pull request!**

If you're fixing a bug, please add a failing test or code that can reproduce the issue.

**Build steps**

1. Build artifacts with `./gradlew assemble`
1. Run Tests `./gradlew test`
1. Run Instrumented Test `./gradlew :sample:connectedAndroidTest`
1. Run Android Lint `./gradlew lint`
1. Run Kotlin Lint `./gradlew detektCheck`

### License

```
Copyright 2018 Ouattara Gninlikpoho Romuald

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
