package com.kudagonish.languagedictionary

import io.reactivex.Observable


sealed interface AppSate{
    data class Success(val data: List<DataModel>): AppSate
    data class Error(val t: Throwable): AppSate
    data class Loading(val process: Int? = null): AppSate


}
interface View {
    fun renderData(appSate: AppSate)
}

interface Presenter<T:AppSate, V: View>{
    fun attachView(view: V)

    fun detachView(view: V)

    fun getData(word: String, isOnline: Boolean)

}

interface Interactor<T>{
    fun getData(word: String, isRemoteResource: Boolean) : Observable<T>
}

interface Repository<T>{
    fun getData(word: String) : Observable<T>
}

interface DataSource<T>{
    fun getData(word: String) : Observable<T>
}