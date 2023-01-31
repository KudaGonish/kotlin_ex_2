package com.kudagonish.languagedictionary.data.remote

import com.kudagonish.languagedictionary.DataModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    fun search(@Query("search") word: String) : Observable<List<DataModel>>
}