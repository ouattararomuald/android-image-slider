package com.ouattararomuald.slider

/** Callback for periodic task event. */
internal interface Looper {

  /** Starts the periodic task. */
  fun startAutoLooping()

  /** Stops the periodic task. */
  fun stopAutoLooping()
}
