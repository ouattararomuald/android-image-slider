package com.slider.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

class MainActivity : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private val groupAdapter = GroupAdapter<ViewHolder>()
  private val slidersSection = Section()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    groupAdapter.add(slidersSection)

    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.apply {
      layoutManager = GridLayoutManager(this@MainActivity, 2)
      adapter = groupAdapter
    }

    Data.URLS.forEach { urls ->
      slidersSection.add(SliderItem(urls))
    }
  }
}
