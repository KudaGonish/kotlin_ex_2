package com.kudagonish.languagedictionary

import io.reactivex.Observable


sealed interface AppState{
    data class Success(val data: List<DataModel>): AppState
    data class Error(val t: Throwable): AppState
    data class Loading(val process: Int? = null): AppState


}
interface View {
    fun renderData(appSate: AppState)
}

interface Presenter<T:AppState, V: View>{
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