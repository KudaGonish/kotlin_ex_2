package com.kudagonish.data.local

import com.kudagonish.models.AppState
import com.kudagonish.models.DataModel
import com.kudagonish.models.DataSourceLocal
import com.kudagonish.models.RepositoryLocal


class LocalRepoImpl( private val dataSourceLocal: DataSourceLocal<List<DataModel>>) :
    RepositoryLocal<List<DataModel>> {
    override suspend fun saveData(appSate: AppState) {
        return dataSourceLocal.saveData(appSate)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSourceLocal.getData("")
    }


}