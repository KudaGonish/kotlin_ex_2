package com.kudagonish.timerdev.domaing.calculator

import com.kudagonish.timerdev.domaing.state.StopwatchState
import com.kudagonish.timerdev.domaing.TimestampProvider

class ElapsedTimeCalculator(private val timestampProvider: TimestampProvider) {
    fun calculate(state: StopwatchState.Running): Long {
        val currentTimestamp = timestampProvider.getMS()
        val timePassedSinceStart = if (currentTimestamp > state.startTime) {
            currentTimestamp - state.startTime
        } else 0
        return timePassedSinceStart + state.elapsedTime
    }
}