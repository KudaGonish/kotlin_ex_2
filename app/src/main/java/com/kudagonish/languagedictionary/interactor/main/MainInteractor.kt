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
    override suspend fun getData(word: String, isRemoteResource: Boolean): AppState {
        return if(isRemoteResource){
            val data = remoteRepository.getData(word)
            AppState.Success(data)
        }
        else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }

}


