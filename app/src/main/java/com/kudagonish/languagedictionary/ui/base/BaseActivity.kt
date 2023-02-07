package com.kudagonish.languagedictionary.ui.base

import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.getSystemService
import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.Presenter
import com.kudagonish.languagedictionary.View

abstract class BaseActivity<T: AppState> : AppCompatActivity() {

    abstract val model: BaseViewModel<T>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        model.getStateLiveData().observe(this) { renderData(it)}
    }
    abstract fun renderData(appState: T)

}