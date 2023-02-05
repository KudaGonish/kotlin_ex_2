package com.kudagonish.languagedictionary.ui.main

import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import com.kudagonish.languagedictionary.ui.base.BaseViewModel
import io.reactivex.observers.DisposableObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class MainViewModel(private val interactor: MainInteractor) : BaseViewModel<AppState>() {


    private val viewModelScope = CoroutineScope(
        Dispatchers.Main +
                SupervisorJob()
    )

    fun getWordsDescriptions(word: String, isOnline: Boolean) {

        viewModelScope.launch {
            try {
                val data = interactor.getData(word, isOnline)
                stateLiveData.value = data
            } catch (e: Exception) {
                stateLiveData.value = AppState.Error(e)
            }
        }

    }

}
