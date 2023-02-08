package com.kudagonish.languagedictionary.data.local

import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSourceLocal
import com.kudagonish.languagedictionary.data.local.models.HistoryEntity
import com.kudagonish.languagedictionary.data.local.models.toEntity

class RoomDataSource(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun saveData(appState: AppState) {
        historyDao.insert(appState.toEntity()?: return)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map(HistoryEntity::toDomainModel)
    }


}

