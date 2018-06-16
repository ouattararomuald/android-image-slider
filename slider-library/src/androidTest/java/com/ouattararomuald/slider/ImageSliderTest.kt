package com.ouattararomuald.slider

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageSliderTest {

  private lateinit var appContext: Context
  private lateinit var imageSlider: ImageSlider

  @Before
  fun setUp() {
    appContext = InstrumentationRegistry.getTargetContext()
    //imageSlider = ImageSlider(appContext)
  }

  @After
  fun tearDown() {
  }

  /** Verifies [ImageSlider] default configuration. */
  @Test
  fun verifyDefaultConfiguration() {
    //assertThat(imageSlider.adapter).isNull()
    //assertThat(imageSlider.pageTransformer).isNull()
  }
}
