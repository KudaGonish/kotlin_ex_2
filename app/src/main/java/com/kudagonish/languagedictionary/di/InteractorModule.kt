package com.kudagonish.languagedictionary.di

import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.Repository
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import dagger.Module
import dagger.Provides
import javax.inject.Named


@Module
class InteractorModule {
    @Provides
    fun provideInteractor(
        @Named(NAME_REMOTE) remoteRepository: Repository<List<DataModel>>,
        @Named(NAME_LOCAL) localRepository: Repository<List<DataModel>>
    ) = MainInteractor(remoteRepository, localRepository)
}