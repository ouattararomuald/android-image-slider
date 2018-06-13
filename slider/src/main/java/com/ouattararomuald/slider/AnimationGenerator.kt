package com.ouattararomuald.slider

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

internal class AnimationGenerator private constructor() {
  companion object {
    private const val DEFAULT_ANIMATION_DURATION_IN_MILLIS = 500L

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
