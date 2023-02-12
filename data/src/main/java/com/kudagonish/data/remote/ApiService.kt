package com.kudagonish.data.remote

import com.kudagonish.models.DataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("words/search")
    suspend fun search(@Query("search") word: String) : List<DataModel>
}