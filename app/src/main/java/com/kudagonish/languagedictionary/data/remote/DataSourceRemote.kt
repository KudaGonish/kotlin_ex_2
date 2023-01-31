package com.kudagonish.languagedictionary.data.remote

import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSource
import io.reactivex.Observable

class DataSourceRemote(private val remoteProvider: RetrofitImolementation = RetrofitImolementation()): DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return remoteProvider.getData(word)
    }
}