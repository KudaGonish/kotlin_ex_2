package com.kudagonish.data.remote

import com.kudagonish.models.DataModel
import com.kudagonish.models.DataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class RetrofitImolementation:
    DataSource<List<DataModel>> {
     override suspend fun getData(word: String): List<DataModel> {
       return createRetrofit().create<ApiService>().search(word)
    }

    private fun createRetrofit() : Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://dictionary.skyeng,ru/api/public/v1")
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .build()

    }

    private fun createOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

}
