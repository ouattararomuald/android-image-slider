[![CircleCI](https://circleci.com/gh/ouattararomuald/android-image-slider.svg?style=svg)](https://circleci.com/gh/ouattararomuald/android-image-slider)
[![](https://img.shields.io/maven-central/v/com.ouattararomuald/slider.svg)](https://search.maven.org/search?q=g:com.ouattararomuald%20a:slider)
[![](https://img.shields.io/badge/code--style-square-green.svg)](https://github.com/square/java-code-styles)

# Image Slider

An image slider for android that let you pick the image loader library that best suits your needs.

<img src="https://imgur.com/Id4u1i8.gif" width=400/>

## Usage

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

`Slider` comes with Picasso as dependency to load images. If you want to use another library,
you must extend `ImageLoader#Factory` and pass your factory to `SliderAdapter`.


## Animations

A slider needs animations between each transition. To create a transition, you must implement `ViewPager.PageTransformer` and pass it to `ImageSlider#pageTransformer`:

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

**Atributes**

The attributes below are available for usage in your `xml` files:

Attributes | Descriptions
------------ | -------------
autoRecoverAfterTouchEvent | Determines whether or not the ImageSlider should recover after user touch event.
indicatorBackground | Reference to a background to be applied to Slider's indicator.
initialSlideDelay | Delay in milliseconds before the first slide change.
initWithAutoCycling | Determines whether or not the ImageSlider should immediately starts its transitions.
sliderBackground | Reference to a background to be applied to Slider.
slideTransitionInterval | Time in milliseconds between successive slide changes.
indicatorFillColor | Color of the filled circle that represents the current page.
indicatorPageColor | Color of the filled circles that represents pages.
indicatorRadius | Radius of the circles. This is also the spacing between circles.
indicatorSnap | Whether or not the selected indicator snaps to the circles.
indicatorStrokeColor | Color of the open circles.
indicatorStrokeWidth | Width of the stroke used to draw the circles.
indicatorsLeftMargin | Indicators left margin size.
indicatorsTopMargin | Indicators top margin size.
indicatorsRightMargin | Indicators right margin size.
indicatorsBottomMargin | Indicators bottom margin size.


## Download

Download the [latest AAR](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.ouattararomuald%22%20AND%20a%3A%22slider%22) or grab via Gradle:

```gradle
implementation 'com.ouattararomuald:slider-glide:2.0.0' // If you use Glide
implementation 'com.ouattararomuald:slider-picasso:2.0.0' // If you use Picasso
```

If you want to use another image loading library include:

```gradle
implementation 'com.ouattararomuald:slider:2.0.0'
```

and create you custom `ImageLoader` by implementing `ImageLoader#Factory` (see `glide-loader` or `picasso-loader`).

or Maven:

```xml
<!-- If you use Glide -->
<dependency>
  <groupId>com.ouattararomuald</groupId>
  <artifactId>slider-glide</artifactId>
  <version>2.0.0</version>
</dependency>
```

```xml
<!-- If you use Picasso -->
<dependency>
  <groupId>com.ouattararomuald</groupId>
  <artifactId>slider-picasso</artifactId>
  <version>2.0.0</version>
</dependency>
```

```xml
<!-- If you want a custom image loader -->
<dependency>
  <groupId>com.ouattararomuald</groupId>
  <artifactId>slider</artifactId>
  <version>2.0.0</version>
</dependency>
```

Snapshots of the development version are available in [Sonatype's snapshots repository](https://oss.sonatype.org/content/repositories/snapshots/).

## Contributing

Contributions you say? Yes please!

**Bug report?**

If at all possible, please attach a minimal sample project or code which reproduces the bug.
Screenshots are also a huge help if the problem is visual.

**Send a pull request!**

If you're fixing a bug, please add a failing test or code that can reproduce the issue.

**Build steps**

1. Build artifacts with `./gradlew assemble`
1. Run Tests `./gradlew test`
1. Run Instrumented Test `./gradlew :sample:connectedAndroidTest`
1. Run Android Lint `./gradlew lint`
1. Run Kotlin Lint `./gradlew detekt`

## License

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
