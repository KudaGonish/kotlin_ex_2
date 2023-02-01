package com.kudagonish.languagedictionary.interactor.main


import com.kudagonish.languagedictionary.AppState
import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.Interactor
import com.kudagonish.languagedictionary.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
): Interactor<AppState>{
    override fun getData(word: String, isRemoteResource: Boolean): Observable<AppState> {
        return if(isRemoteResource)
            remoteRepository.getData(word).map{ AppState.Success(it)}
        else
            localRepository.getData(word).map { AppState.Success(it) }
    }

}


