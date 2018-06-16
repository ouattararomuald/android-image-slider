package com.ouattararomuald.slider

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.animation.TranslateAnimation
import android.widget.ImageView
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnimationGeneratorTest {

  private lateinit var appContext: Context
  private lateinit var imageView: ImageView

  /** Associate each [AnimationType] with its expected class. */
  private val animationClassByAnimationType = mapOf(
      AnimationType.TRANSLATION to TranslateAnimation::class.java
  )

  @Before
  fun setUp() {
    appContext = InstrumentationRegistry.getTargetContext()
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
