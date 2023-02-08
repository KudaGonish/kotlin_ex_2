package com.kudagonish.languagedictionary.interactor.history

import android.provider.ContactsContract.Data
import com.kudagonish.languagedictionary.*

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : IHistoryInteractor{
    override suspend fun getData(): AppState {

        return AppState.Success(repositoryLocal.getData(""))
    }

}