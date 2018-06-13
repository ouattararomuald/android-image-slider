package com.ouattararomuald.slider

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SliderAdapterTest {

  @Test(expected = IllegalArgumentException::class)
  fun sliderAdapterWithZeroOrLessSlidesShouldFails() {
    val appContext = InstrumentationRegistry.getTargetContext()

    val sliderAdapter = SliderAdapter(appContext, -1, object : ImageLoaderCallback {
      /** Loads image for the [imageView] at the given [position]. */
      override fun loadImageFor(imageView: ImageView, position: Int) {
      }
    })
  }
}
