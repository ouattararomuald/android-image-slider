package com.ouattararomuald.slider

import android.content.Context
import android.os.Handler
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.widget.ImageView
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.fail
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PicassoImageLoaderTest {

  private lateinit var appContext: Context
  private lateinit var imageView: ImageView

  @Before
  fun setUp() {
    appContext = InstrumentationRegistry.getTargetContext()
    imageView = ImageView(appContext)
  }

  @Test
  fun loadImageWithWrongResIdInputShouldNotThrowsException() {
    val picassoImageLoader = PicassoImageLoader(
        errorResId = R.drawable.default_indicator,
        placeholderResId = R.drawable.default_indicator
    )
    val url = "http://i.imgur.com/CqmBjo5.jpg"

    assertThat(picassoImageLoader).isNotNull()
    load(url, imageView, picassoImageLoader)
  }

  private fun load(url: String, imageView: ImageView, picassoImageLoader: PicassoImageLoader) {
    val mainHandler = Handler(appContext.mainLooper)
    mainHandler.post {
      try {
        picassoImageLoader.load(url, imageView)
      } catch (ex: IllegalArgumentException) {
        fail("Wrong errorResId or placeholderResId should not raise exception")
      }
    }
  }
}