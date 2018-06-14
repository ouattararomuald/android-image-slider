package com.ouattararomuald.slider

class DataGenerator private constructor() {
  companion object {
    fun generateTexts(quantity: Int): List<String> {
      val descriptions = mutableListOf<String>()
      (1..quantity).forEach {
        descriptions.add("Text $quantity")
      }

      return descriptions
    }
  }
}