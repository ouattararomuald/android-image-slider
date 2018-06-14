package com.ouattararomuald.slider

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SliderAdapterTest {
  companion object {
    const val QUANTITY = 5
  }

  private lateinit var appContext: Context
  private lateinit var sliderAdapter: SliderAdapter

  @Before
  fun setUp() {
    appContext = InstrumentationRegistry.getTargetContext()
  }

  @Test(expected = IllegalArgumentException::class)
  fun sliderAdapterWithZeroImageShouldFails() {
    val sliderAdapter = SliderAdapter(
        appContext,
        PicassoImageLoaderFactory(),
        imageUrls = arrayListOf()
    )
  }

  @Test(expected = IllegalArgumentException::class)
  fun sliderAdapterWithDifferentImageSizeAndDescriptionsSizeShouldFails() {
    val sliderAdapter = createSliderAdapter(
        numberOfImages = QUANTITY,
        numberOfDescriptions = QUANTITY * 2
    )
  }

  @Test
  fun sliderAdapterWithSameImagesSizeAndDescriptionsSizeShouldSuccess() {
    val sliderAdapter = createSliderAdapter(
        numberOfImages = QUANTITY,
        numberOfDescriptions = QUANTITY
    )

    assertThat(sliderAdapter).isNotNull()
  }

  private fun createSliderAdapter(numberOfImages: Int, numberOfDescriptions: Int): SliderAdapter {
    return SliderAdapter(
        appContext,
        PicassoImageLoaderFactory(),
        imageUrls = DataGenerator.generateTexts(quantity = numberOfImages),
        descriptions = DataGenerator.generateTexts(quantity = numberOfDescriptions)
    )
  }
}
