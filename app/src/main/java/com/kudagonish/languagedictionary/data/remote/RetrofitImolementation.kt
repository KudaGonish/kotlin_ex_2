package com.kudagonish.languagedictionary.data.remote

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSource
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitImolementation: DataSource<List<DataModel>> {
    override fun getData(word: String): Observable<List<DataModel>> {
       return createRetrofit().create<ApiService>().search(word)
    }

    private fun createRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng,ru/api/public/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(createOkHttpClient())
            .build()

    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

}
