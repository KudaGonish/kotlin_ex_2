package com.kudagonish.data.remote

import com.kudagonish.models.DataModel
import com.kudagonish.models.DataSource

class DataSourceRemote(private val remoteProvider: RetrofitImolementation = RetrofitImolementation()):
    DataSource<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return remoteProvider.getData(word)
    }
}