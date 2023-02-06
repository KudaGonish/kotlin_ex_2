package com.kudagonish.timerdev.domaing.calculator

import com.kudagonish.timerdev.domaing.state.StopwatchState
import com.kudagonish.timerdev.domaing.TimestampProvider

class StopwatchStateCalculator(
    private val timestampProvider: TimestampProvider,
    private val elapsedTimeCalculator: ElapsedTimeCalculator
) {
    fun calculateRunningState(oldState: StopwatchState): StopwatchState.Running = when (oldState) {
        is StopwatchState.Running -> oldState
        is StopwatchState.Paused -> {
            StopwatchState.Running(
                startTime = timestampProvider.getMS(),
                elapsedTime = oldState.elapsedTime
            )
        }
    }

    fun calculatePausedState(oldState: StopwatchState): StopwatchState.Paused = when(oldState){
        is StopwatchState.Running -> {
            val elapsedTime = elapsedTimeCalculator.calculate(oldState)
            StopwatchState.Paused(elapsedTime = elapsedTime)
        }
        is StopwatchState.Paused -> oldState
    }
}