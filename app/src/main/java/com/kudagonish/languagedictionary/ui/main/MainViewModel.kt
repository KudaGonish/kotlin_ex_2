package com.kudagonish.languagedictionary.ui.main

import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import com.kudagonish.languagedictionary.ui.base.BaseViewModel
import io.reactivex.observers.DisposableObserver


class MainViewModel ( private val interactor: MainInteractor) : BaseViewModel<AppState>() {


    fun getWordsDescriptions(word: String, isOnline: Boolean) {
        compositeDisposable.add(interactor.getData(word, isOnline).subscribeOn(schedulerProvider.io)
            .observeOn(schedulerProvider.ui)
            .doOnSubscribe { stateLiveData.value = AppState.Loading(null) }
            .subscribeWith(getObserver()))
    }

    private fun getObserver() = object : DisposableObserver<AppState>() {
        override fun onNext(t: AppState) {
            stateLiveData.value = t
        }

        override fun onError(e: Throwable) {
            stateLiveData.value = AppState.Error(e)
        }

        override fun onComplete() = Unit

    }
}
