package com.ouattararomuald.slider

import android.os.Handler
import java.util.Timer
import java.util.TimerTask

/**
 * [Looper] which creates a periodic task.
 *
 * Once the task is started, it will run periodically until it is stopped.
 *
 * @property delayInMillis Delay in milliseconds before task is to be executed.
 * @property periodInMillis Time in milliseconds between successive task executions.
 * @property onLoop Invoked during task execution.
 */
internal class LoopHandler(
  private val delayInMillis: Long,
  private val periodInMillis: Long,
  private val onLoop: () -> Unit
) : Looper {

  private var timer: Timer? = null
  private var periodicTimerTask: TimerTask? = null

  /** Starts the periodic task. */
  override fun startAutoLooping() {
    timer?.cancel()
    periodicTimerTask?.cancel()

    timer = Timer()
    periodicTimerTask = PeriodicTimerTask()
    timer?.scheduleAtFixedRate(periodicTimerTask, delayInMillis, periodInMillis)
  }

  /** Stops the periodic task. */
  override fun stopAutoLooping() {
    timer?.cancel()
    periodicTimerTask?.cancel()
  }

  private inner class PeriodicTimerTask : TimerTask() {
    override fun run() {
      val mainHandler = Handler(android.os.Looper.getMainLooper())
      mainHandler.post {
        onLoop()
      }
    }
  }
}
