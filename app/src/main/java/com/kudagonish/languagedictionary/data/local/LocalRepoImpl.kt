package com.kudagonish.languagedictionary.data.local

import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSourceLocal
import com.kudagonish.languagedictionary.RepositoryLocal

class LocalRepoImpl( private val dataSourceLocal: DataSourceLocal<List<DataModel>>) : RepositoryLocal<List<DataModel>>{
    override suspend fun saveData(appSate: AppState) {
        return dataSourceLocal.saveData(appSate)
    }

    override suspend fun getData(word: String): List<DataModel> {
        return dataSourceLocal.getData("")
    }


}