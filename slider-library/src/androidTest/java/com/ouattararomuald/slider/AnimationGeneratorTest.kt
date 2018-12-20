package com.ouattararomuald.slider

import android.content.Context
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import androidx.test.platform.app.InstrumentationRegistry
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test

class AnimationGeneratorTest {

  private lateinit var appContext: Context
  private lateinit var imageView: ImageView

  /** Associate each [AnimationType] with its expected class. */
  private val animationClassByAnimationType = mapOf(
      AnimationType.TRANSLATION to TranslateAnimation::class.java
  )

  @Before
  fun setUp() {
    appContext = InstrumentationRegistry.getInstrumentation().context
    imageView = ImageView(appContext)
  }

  @Test
  fun validateAnimationClassForAnimationTypes() {
    animationClassByAnimationType.forEach { animationType, expectedAnimationClass ->
      val actualAnimation = AnimationGenerator.generateEntranceAnimation(
          imageView, animationType
      )
      assertThat(actualAnimation).isInstanceOf(expectedAnimationClass)
    }
  }
}
