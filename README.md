# Image Slider

An image slider for android that let you pick the image loader library that best suits your needs.

<img src="https://imgur.com/Id4u1i8.gif" width=400/>

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

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageSlider = findViewById(R.id.image_slider)
    imageSlider.adapter = SliderAdapter(this, SLIDE_NUMBER, object: Callback {
      override fun loadImageFor(imageView: ImageView, position: Int) {
        // Load image using for favorite image loader (ex: fresco, glide or picasso...)
      }
    })
  }
}
```

A slider needs animations between each transition. To create a transition, all you need is to implement `ViewPager.PageTransformer` and pass it to `ImageSlider#pageTransformer`:

```kotlin
class MainActivity : AppCompatActivity() {
  
  companion object {
    private const val SLIDE_NUMBER = 10
  }

  private lateinit var imageSlider: ImageSlider

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageSlider = findViewById(R.id.image_slider)
    imageSlider.adapter = SliderAdapter(this, SLIDE_NUMBER, object: Callback {
      override fun loadImageFor(imageView: ImageView, position: Int) {
        // Load image using for favorite image loader (ex: fresco, glide or picasso...)
      }
    })
    
    // here we use : https://github.com/miaoyongjun/PageTransformer
    // which provides various ViewPager.PageTransformer implementations.
    imageSlider.pageTransformer = MagicTransformer.getPageTransformer(
       TransitionEffect.Cube
    )
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


## Download

Download the [latest AAR](https://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.ouattararomuald%22%20AND%20a%3A%22slider%22) or grab via Gradle:

```gradle
implementation 'com.ouattararomuald:slider:0.1.0'
```

or Maven:

```xml
<dependency>
  <groupId>com.ouattararomuald</groupId>
  <artifactId>slider</artifactId>
  <version>0.1.0</version>
</dependency>
```

## Contributing

Contributions you say? Yes please!

**Bug report?**

If at all possible, please attach a minimal sample project or code which reproduces the bug.
Screenshots are also a huge help if the problem is visual.

**Send a pull request!**

If you're fixing a bug, please add a failing test or code that can reproduce the issue.

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
