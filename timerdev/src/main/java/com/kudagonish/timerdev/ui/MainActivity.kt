package com.kudagonish.timerdev.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kudagonish.timerdev.databinding.ActivityMainBinding
import com.kudagonish.timerdev.domaing.TimestampMsFormatter
import com.kudagonish.timerdev.domaing.TimestampProvider
import com.kudagonish.timerdev.domaing.calculator.ElapsedTimeCalculator
import com.kudagonish.timerdev.domaing.calculator.StopwatchStateCalculator
import com.kudagonish.timerdev.domaing.state.StopwatchOrchestrator
import com.kudagonish.timerdev.domaing.state.StopwatchStateHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val timestampProvider = object : TimestampProvider {
        override fun getMS(): Long {
            return System.currentTimeMillis()
        }
    }
private val scope = CoroutineScope(Dispatchers.Main + SupervisorJob())
    private val elapsedTimeCalculator = ElapsedTimeCalculator(timestampProvider)
    private val stopwatchOrchestrator = StopwatchOrchestrator(
        StopwatchStateHolder(
            StopwatchStateCalculator(
                timestampProvider,
                elapsedTimeCalculator
            ),
            elapsedTimeCalculator,
            TimestampMsFormatter()
        ),
        scope
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        scope.launch {
            stopwatchOrchestrator.ticker
                .collect {
                    binding.textTime.text = it
                }
        }

        binding.startButton.setOnClickListener{ stopwatchOrchestrator.start()}
        binding.stopButton.setOnClickListener{ stopwatchOrchestrator.stop()}
        binding.pauseButton.setOnClickListener{ stopwatchOrchestrator.pause( )}

    }
}