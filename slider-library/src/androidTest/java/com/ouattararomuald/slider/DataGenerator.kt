package com.ouattararomuald.slider

class DataGenerator private constructor() {
  companion object {
    fun generateTexts(quantity: Int): List<String> {
      val descriptions = mutableListOf<String>()
      for (i in 1..quantity) {
        descriptions.add("Text $i")
      }

      return descriptions
    }
  }
}