package com.kudagonish.languagedictionary.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kudagonish.models.AppState

abstract class BaseViewModel<T: AppState> (
    protected val stateLiveData: MutableLiveData<T> = MutableLiveData()
):ViewModel(){

    open fun getStateLiveData(): LiveData<T> = stateLiveData

}
