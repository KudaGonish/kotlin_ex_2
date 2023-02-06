package com.kudagonish.timerdev.domaing.state

import com.kudagonish.timerdev.domaing.TimestampMsFormatter
import com.kudagonish.timerdev.domaing.calculator.ElapsedTimeCalculator
import com.kudagonish.timerdev.domaing.calculator.StopwatchStateCalculator

class StopwatchStateHolder (
    private val stopwatchStateCalculator: StopwatchStateCalculator,
    private val elapsedTimeCalculator: ElapsedTimeCalculator,
    private val timestampMsFormatter: TimestampMsFormatter
)
{
    var currentState: StopwatchState = StopwatchState.Paused(0)
        private set

    fun start(){
        currentState = stopwatchStateCalculator.calculateRunningState(currentState)
    }
    fun pause(){
        currentState = stopwatchStateCalculator.calculatePausedState(currentState)
    }
    fun stop(){
        currentState = StopwatchState.Paused(0)
    }

    override fun toString(): String {
        val elapsedTime = when(val currentState = currentState){
            is StopwatchState.Paused -> currentState.elapsedTime
            is StopwatchState.Running -> elapsedTimeCalculator.calculate(currentState)
        }
        return timestampMsFormatter.format(elapsedTime)
    }
}