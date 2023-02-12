package com.kudagonish.languagedictionary.interactor.history

import com.kudagonish.models.AppState
import com.kudagonish.models.DataModel
import com.kudagonish.models.IHistoryInteractor
import com.kudagonish.models.RepositoryLocal

class HistoryInteractor(
    private val repositoryLocal: RepositoryLocal<List<DataModel>>
) : IHistoryInteractor {
    override suspend fun getData(): AppState {

        return AppState.Success(repositoryLocal.getData(""))
    }

}