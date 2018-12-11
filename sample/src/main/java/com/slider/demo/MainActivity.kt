package com.slider.demo

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Section
import com.xwray.groupie.ViewHolder

class MainActivity : AppCompatActivity() {

  private lateinit var recyclerView: RecyclerView
  private val groupAdapter = GroupAdapter<ViewHolder>()
  private var section = Section()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    groupAdapter.add(section)

    recyclerView = findViewById(R.id.recycler_view)
    recyclerView.apply {
      layoutManager = GridLayoutManager(this@MainActivity, 2)
      adapter = groupAdapter
    }

    fillSection(R.id.menu_picasso_item)
  }

  override fun onPause() {
    super.onPause()
    groupAdapter.remove(section)
  }

  override fun onCreateOptionsMenu(menu: Menu): Boolean {
    val inflater: MenuInflater = menuInflater
    inflater.inflate(R.menu.image_loaders_menu, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    fillSection(item.itemId)
    return true
  }

  private fun fillSection(menuId: Int) {
    val imageLoaderType = getImageLoaderType(menuId)

    groupAdapter.remove(section)
    section = Section()
    Data.URLS.keys.forEach { pageTransformer ->
      section.add(
          SliderItem(
              imageLoaderType,
              Data.URLS[pageTransformer]!!.toTypedArray(),
              pageTransformer
          )
      )
    }
    groupAdapter.add(section)
  }

  private fun getImageLoaderType(menuId: Int): ImageLoaderType = when (menuId) {
    R.id.menu_glide_item -> ImageLoaderType.GLIDE
    R.id.menu_picasso_item -> ImageLoaderType.PICASSO
    else -> ImageLoaderType.PICASSO
  }
}
