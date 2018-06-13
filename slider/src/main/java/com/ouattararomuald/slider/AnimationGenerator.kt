package com.ouattararomuald.slider

import android.view.View
import android.view.animation.Animation
import android.view.animation.TranslateAnimation

internal class AnimationGenerator private constructor() {
  companion object {
    fun generateEntranceAnimation(view: View, animationType: AnimationType): Animation {
      when (animationType) {
        AnimationType.TRANSLATION -> {
          val translateAnimation = TranslateAnimation(
              view.x,
              view.x,
              view.height.toFloat(),
              0f
          )
          translateAnimation.apply {
            duration = 500
            fillAfter = true
          }

          return translateAnimation
        }
      }
    }

    fun generateExitAnimation(view: View, animationType: AnimationType): Animation {
      when (animationType) {
        AnimationType.TRANSLATION -> {
          val translateAnimation = TranslateAnimation(
              view.x,
              view.x,
              0f,
              view.height.toFloat()
          )
          translateAnimation.apply {
            duration = 500
            fillAfter = true
          }

          return translateAnimation
        }
      }
    }
  }
}