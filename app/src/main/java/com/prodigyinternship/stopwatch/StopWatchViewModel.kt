package com.prodigyinternship.stopwatch

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class StopwatchViewModel : ViewModel() {
    private var startTime = 0L
    private var elapsedTime = 0L
    private var isRunning = false
    private var isPaused = false

    // Current stopwatch display time in minutes, seconds, and milliseconds
    var minutes by mutableIntStateOf(0)
        private set
    var seconds by mutableIntStateOf(0)
        private set
    var milliseconds by mutableIntStateOf(0)
        private set

    // Start or resume the stopwatch
    fun start() {
        if (!isRunning) {
            isRunning = true
            isPaused = false
            startTime = System.currentTimeMillis() - elapsedTime
            viewModelScope.launch {
                while (isRunning) {
                    elapsedTime = System.currentTimeMillis() - startTime
                    updateDisplayTime(elapsedTime)
                    delay(16) // Update approximately every 16ms (close to 60fps)
                }
            }
        }
    }

    // Pause the stopwatch
    fun pause() {
        isRunning = false
        isPaused = true
    }

    // Reset the stopwatch
    fun reset() {
        isRunning = false
        isPaused = false
        elapsedTime = 0L
        updateDisplayTime(elapsedTime)
    }

    // Helper function to update the displayed time
    private fun updateDisplayTime(elapsedTime: Long) {
        milliseconds = (elapsedTime % 1000).toInt() / 10
        seconds = (elapsedTime / 1000 % 60).toInt()
        minutes = (elapsedTime / 60000 % 60).toInt()
    }
}
