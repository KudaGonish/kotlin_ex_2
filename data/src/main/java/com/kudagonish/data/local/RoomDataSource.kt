package com.kudagonish.data.local

import com.kudagonish.data.local.model.HistoryEntity
import com.kudagonish.data.local.model.toEntity
import com.kudagonish.models.AppState
import com.kudagonish.models.DataModel
import com.kudagonish.models.DataSourceLocal

class RoomDataSource(private val historyDao: HistoryDao) : DataSourceLocal<List<DataModel>> {
    override suspend fun saveData(appState: AppState) {
        historyDao.insert(appState.toEntity()?: return)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return historyDao.all().map(HistoryEntity::toDomainModel)
    }


}

