package com.kudagonish.languagedictionary.interactor.main


import com.kudagonish.models.*

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: RepositoryLocal<List<DataModel>>
): Interactor<AppState> {
    override suspend fun getData(word: String, isRemoteResource: Boolean): AppState {
        return if(isRemoteResource){
            val data = remoteRepository.getData(word)
            val appState = AppState.Success(data)
            localRepository.saveData(appState)
            appState
        }
        else {
            val data = localRepository.getData(word)
            AppState.Success(data)
        }
    }

}


