package com.kudagonish.languagedictionary.di

import com.kudagonish.languagedictionary.DataModel
import com.kudagonish.languagedictionary.DataSource
import com.kudagonish.languagedictionary.Repository
import com.kudagonish.languagedictionary.data.RepoImpl
import com.kudagonish.languagedictionary.data.remote.RetrofitImolementation
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import com.kudagonish.languagedictionary.ui.main.MainViewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val application = module {
    single<DataSource<List<DataModel>>>(named(NAME_REMOTE)) {
        RetrofitImolementation()
    }
    single <Repository<List<DataModel>>>{
        RepoImpl(get (named(NAME_REMOTE)))
    }


    single<DataSource<List<DataModel>>>(named(NAME_LOCAL)) {
        RetrofitImolementation()
    }
    single <Repository<List<DataModel>>>{
        RepoImpl(get (named(NAME_LOCAL)))
    }
}

val mainScreen = module {
    factory { MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL))) }
    factory { MainViewModel(get()) }
}