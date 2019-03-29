/*
 * Copyright (C) 2011 Patrik Akerfeldt
 * Copyright (C) 2011 Jake Wharton
 * Copyright (C) 2019 Romuald Ouattara
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ouattararomuald.slider.indicator

import androidx.viewpager.widget.ViewPager

/**
 * A PageIndicator is responsible to show an visual indicator on the total views
 * number and the current visible view.
 */
internal interface PageIndicator: ViewPager.OnPageChangeListener {
  /**
   * Bind the indicator to a ViewPager.
   *
   * @param view
   */
  fun setViewPager(view: ViewPager)

  /**
   * Bind the indicator to a ViewPager.
   *
   * @param view
   * @param initialPosition
   */
  fun setViewPager(view: ViewPager, initialPosition: Int)

  /**
   *
   * Set the current page of both the ViewPager and indicator.
   *
   *
   * This **must** be used if you need to set the page before
   * the views are drawn on screen (e.g., default start page).
   *
   * @param item
   */
  fun setCurrentItem(item: Int)

  /**
   * Set a page change listener which will receive forwarded events.
   *
   * @param listener
   */
  fun setOnPageChangeListener(listener: ViewPager.OnPageChangeListener)

  /**
   * Notify the indicator that the fragment list has changed.
   */
  fun notifyDataSetChanged()
}