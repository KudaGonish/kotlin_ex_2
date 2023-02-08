package com.kudagonish.languagedictionary.ui.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.IHistoryInteractor
import com.kudagonish.languagedictionary.interactor.history.HistoryInteractor
import com.kudagonish.languagedictionary.ui.base.BaseViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelChildren
import kotlinx.coroutines.launch

class HistoryViewModel(private val interactor: IHistoryInteractor) : BaseViewModel<AppState>() {

    fun getData(){
        stateLiveData.value = AppState.Loading(null)
        viewModelScope.coroutineContext.cancelChildren()
        viewModelScope.launch {
            val data = interactor.getData()
            stateLiveData.value = data
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancelChildren()
    }
    init {
        getData()
    }
}