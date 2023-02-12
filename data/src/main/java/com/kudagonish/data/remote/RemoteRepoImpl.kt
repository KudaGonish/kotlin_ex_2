package com.kudagonish.data.remote

import com.kudagonish.models.DataModel
import com.kudagonish.models.DataSource
import com.kudagonish.models.Repository

class RemoteRepoImpl(private val dataSource: DataSource<List<DataModel>>):
    Repository<List<DataModel>> {
    override suspend fun getData(word: String): List<DataModel> {
        return dataSource.getData(word)
    }

}