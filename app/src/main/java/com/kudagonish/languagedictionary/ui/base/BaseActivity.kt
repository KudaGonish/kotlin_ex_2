package com.kudagonish.languagedictionary.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.kudagonish.models.AppState

abstract class BaseActivity<T: AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.getStateLiveData().observe(this) { renderData(it)}
    }
    abstract fun renderData(appState: T)

}