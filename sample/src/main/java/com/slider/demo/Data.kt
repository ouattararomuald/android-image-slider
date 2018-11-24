package com.slider.demo

import miaoyongjun.pagetransformer.MagicTransformer
import miaoyongjun.pagetransformer.TransitionEffect

internal class Data private constructor() {
  
  companion object {
    private const val BASE = "http://i.imgur.com/"
    private const val EXT = ".jpg"
    val URLS = mapOf(
        MagicTransformer.getPageTransformer(TransitionEffect.Accordion) to arrayOf(
            "CqmBjo5",
            "zkaAooq",
            "0gqnEaY"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Alpha) to arrayOf(
            "9gbQ7YR",
            "aFhEEby"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Cube) to arrayOf(
            "0E2tgV7",
            "P5JLfjk",
            "nz67a4F"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Default) to arrayOf(
            "dFH34N5",
            "FI49ftb"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Depth) to arrayOf(
            "DvpvklR",
            "DNKnbG8"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Fade) to arrayOf(
            "yAdbrLp",
            "55w5Km7",
            "NIwNTMR"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Flip) to arrayOf(
            "DAl0KB8",
            "xZLIYFV",
            "HvTyeh3",
            "Ig9oHCM"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.InRightDown) to arrayOf(
            "7GUv9qa",
            "i5vXmXp",
            "glyvuXg"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.InRightUp) to arrayOf(
            "u6JF6JZ",
            "ExwR7ap"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Rotate) to arrayOf(
            "Q54zMKT",
            "9t6hLbm"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.SlowBackground) to arrayOf(
            "F8n3Ic6",
            "P5ZRSvT"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Stack) to arrayOf(
            "jbemFzr",
            "8B7haIK",
            "aSeTYQr"
        ).toImageUrls(),
        MagicTransformer.getPageTransformer(TransitionEffect.Zoom) to arrayOf(
            "OKvWoTh",
            "zD3gT4Z",
            "z77CaIt"
        ).toImageUrls()
    )

    fun generateDescriptions(number: Int = 1): ArrayList<String> {
      val descriptions: ArrayList<String> = ArrayList(number)

      (1..number).forEach {
        descriptions.add("Description $it")
      }

      return descriptions
    }
    
    private fun Array<String>.toImageUrls(): List<String> = map { it.toJpegImgurUrl() }

    private fun String.toJpegImgurUrl(): String = BASE + this + EXT
  }
}
