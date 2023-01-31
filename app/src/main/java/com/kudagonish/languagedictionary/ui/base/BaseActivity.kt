package com.kudagonish.languagedictionary.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.kudagonish.languagedictionary.AppSate
import com.kudagonish.languagedictionary.Presenter
import com.kudagonish.languagedictionary.View

abstract class BaseActivity<T: AppSate> : AppCompatActivity(), View {
    protected lateinit var presenter: Presenter<T, View>

    protected abstract fun createPresenter(): Presenter<T, View>

    abstract override fun renderData(appSate: AppSate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = createPresenter()

    }

    override fun onStart() {
        super.onStart()
        presenter.attachView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.detachView(this)
    }
}