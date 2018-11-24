package com.ouattararomuald.slider

import androidx.annotation.VisibleForTesting
import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

/**
 * This utility class generates entrance and/or exit animations.
 *
 * - **Entrance animations** are played while the view is being displayed.
 * - **Exit animations** are played before the view disappear.
 */
internal class AnimationGenerator private constructor() {
  companion object {
    @VisibleForTesting internal const val DEFAULT_ANIMATION_DURATION_IN_MILLIS = 500L

    fun generateEntranceAnimation(view: View, animationType: AnimationType): Animation {
      when (animationType) {
        AnimationType.TRANSLATION -> {
          val toYDelta = 0f
          val translateAnimation = TranslateAnimation(
              view.x,
              view.x,
              view.height.toFloat(),
              toYDelta
          )
          translateAnimation.apply {
            duration = DEFAULT_ANIMATION_DURATION_IN_MILLIS
            fillAfter = true
          }

          return translateAnimation
        }
      }
    }

    fun generateExitAnimation(view: View, animationType: AnimationType): Animation {
      when (animationType) {
        AnimationType.TRANSLATION -> {
          val fromYDelta = 0f
          val translateAnimation = TranslateAnimation(
              view.x,
              view.x,
              fromYDelta,
              view.height.toFloat()
          )
          translateAnimation.apply {
            duration = DEFAULT_ANIMATION_DURATION_IN_MILLIS
            fillAfter = true
          }

          return translateAnimation
        }
      }
    }
  }
}
