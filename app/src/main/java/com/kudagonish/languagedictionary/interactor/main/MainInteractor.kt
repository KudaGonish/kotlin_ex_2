package com.kudagonish.languagedictionary.interactor.main


import com.kudagonish.languagedictionary.AppSate
import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.Interactor
import com.kudagonish.languagedictionary.Repository
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<List<DataModel>>,
    private val localRepository: Repository<List<DataModel>>
): Interactor<AppSate>{
    override fun getData(word: String, isRemoteResource: Boolean): Observable<AppSate> {
        return if(isRemoteResource)
            remoteRepository.getData(word).map{ AppSate.Success(it)}
        else
            localRepository.getData(word).map { AppSate.Success(it) }
    }

}


