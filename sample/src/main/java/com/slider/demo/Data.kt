package com.slider.demo

import miaoyongjun.pagetransformer.MagicTransformer
import miaoyongjun.pagetransformer.TransitionEffect

internal class Data {
  companion object {
    private const val BASE = "http://i.imgur.com/"
    private const val EXT = ".jpg"
    val URLS = mapOf(
        MagicTransformer.getPageTransformer(TransitionEffect.Accordion) to arrayOf(
            BASE + "CqmBjo5" + EXT,
            BASE + "zkaAooq" + EXT,
            BASE + "0gqnEaY" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Alpha) to arrayOf(
            BASE + "9gbQ7YR" + EXT,
            BASE + "aFhEEby" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Cube) to arrayOf(
            BASE + "0E2tgV7" + EXT,
            BASE + "P5JLfjk" + EXT,
            BASE + "nz67a4F" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Default) to arrayOf(
            BASE + "dFH34N5" + EXT,
            BASE + "FI49ftb" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Depth) to arrayOf(
            BASE + "DvpvklR" + EXT,
            BASE + "DNKnbG8" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Fade) to arrayOf(
            BASE + "yAdbrLp" + EXT,
            BASE + "55w5Km7" + EXT,
            BASE + "NIwNTMR" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Flip) to arrayOf(
            BASE + "DAl0KB8" + EXT,
            BASE + "xZLIYFV" + EXT,
            BASE + "HvTyeh3" + EXT,
            BASE + "Ig9oHCM" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.InRightDown) to arrayOf(
            BASE + "7GUv9qa" + EXT,
            BASE + "i5vXmXp" + EXT,
            BASE + "glyvuXg" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.InRightUp) to arrayOf(
            BASE + "u6JF6JZ" + EXT,
            BASE + "ExwR7ap" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Rotate) to arrayOf(
            BASE + "Q54zMKT" + EXT,
            BASE + "9t6hLbm" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.SlowBackground) to arrayOf(
            BASE + "F8n3Ic6" + EXT,
            BASE + "P5ZRSvT" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Stack) to arrayOf(
            BASE + "jbemFzr" + EXT,
            BASE + "8B7haIK" + EXT,
            BASE + "aSeTYQr" + EXT
        ),
        MagicTransformer.getPageTransformer(TransitionEffect.Zoom) to arrayOf(
            BASE + "OKvWoTh" + EXT,
            BASE + "zD3gT4Z" + EXT,
            BASE + "z77CaIt" + EXT
        )
    )
  }
}