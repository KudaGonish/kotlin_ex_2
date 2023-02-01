package com.kudagonish.languagedictionary.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider



class ViewModelFactory @Inject constructor(
    private val viewModels: MutableMap<Class<out ViewModel>, Provider<ViewModel>>
    ) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        val creator = viewModels[modelClass]
        return try {
            creator?.get() as T
        } catch (ex: Exception){
            throw RuntimeException(ex)
        }
    }
}