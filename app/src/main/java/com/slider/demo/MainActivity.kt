package com.slider.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import com.ouattararomuald.slider.Callback
import com.ouattararomuald.slider.ImageSlider
import com.ouattararomuald.slider.SliderAdapter
import com.squareup.picasso.Picasso

class MainActivity : AppCompatActivity() {

  private lateinit var imageSlider: ImageSlider

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    imageSlider = findViewById(R.id.image_slider)
    imageSlider.adapter = SliderAdapter(this, 10, object: Callback {
      override fun loadImageFor(imageView: ImageView, position: Int) {
        Picasso.get().load(Data.URLS[position]).into(imageView)
      }
    })
  }
}
