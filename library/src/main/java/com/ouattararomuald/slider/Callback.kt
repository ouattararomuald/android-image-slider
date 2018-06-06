package com.ouattararomuald.slider

import android.widget.ImageView

interface Callback {

  fun loadImageFor(imageView: ImageView, position: Int)
}