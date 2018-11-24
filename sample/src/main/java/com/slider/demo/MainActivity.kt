package com.slider.demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

class MainActivity : AppCompatActivity() {

  private lateinit var recyclerView: androidx.recyclerview.widget.RecyclerView
  private val groupAdapter = GroupAdapter<ViewHolder>()
  private val slidersSection = Section()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    groupAdapter.add(slidersSection)

    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.apply {
      layoutManager = androidx.recyclerview.widget.GridLayoutManager(this@MainActivity, 2)
      adapter = groupAdapter
    }

    Data.URLS.keys.forEach { pageTransformer ->
      slidersSection.add(SliderItem(Data.URLS[pageTransformer]!!, pageTransformer))
    }
  }
}
