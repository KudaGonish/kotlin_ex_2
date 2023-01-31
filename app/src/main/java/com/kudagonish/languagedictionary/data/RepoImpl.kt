package com.kudagonish.languagedictionary.data

import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSource
import com.kudagonish.languagedictionary.Repository
import io.reactivex.Observable

class RepoImpl(private val dataSource: DataSource<List<DataModel>>): Repository<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
        return dataSource.getData(word)
    }

}