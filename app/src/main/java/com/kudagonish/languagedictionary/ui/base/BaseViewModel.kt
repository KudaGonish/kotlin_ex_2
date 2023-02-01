package com.kudagonish.languagedictionary.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.rx.ISchedulerProvider
import com.kudagonish.languagedictionary.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel<T: AppState> (
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData(),
    protected val schedulerProvider : ISchedulerProvider = SchedulerProvider()
):ViewModel(){
    protected val compositeDisposable = CompositeDisposable()

    open fun getStateLiveData(): LiveData<T> = stateLiveData


    override fun onCleared() {
        compositeDisposable.clear()

    }
}
