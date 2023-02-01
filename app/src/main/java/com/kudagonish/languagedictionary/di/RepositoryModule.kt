package com.kudagonish.languagedictionary.di

import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSource
import com.kudagonish.languagedictionary.Repository
import com.kudagonish.languagedictionary.data.RepoImpl
import com.kudagonish.languagedictionary.data.remote.RetrofitImolementation
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton


@Module
class RepositoryModule {
    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRemoteRepo(@Named(NAME_REMOTE) dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>>{
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideLocalRepo(@Named(NAME_LOCAL) dataSource: DataSource<List<DataModel>>) : Repository<List<DataModel>>{
        return RepoImpl(dataSource)
    }

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    internal fun provideRemoteDataSource(): DataSource<List<DataModel>>
      = RetrofitImolementation()


    @Provides
    @Singleton
    @Named(NAME_LOCAL )
    internal fun provideLocalDataSource(): DataSource<List<DataModel>>
            = RetrofitImolementation()

}