package com.kudagonish.languagedictionary.di

import androidx.room.Room
import com.kudagonish.data.local.HistoryDatabase
import com.kudagonish.data.local.LocalRepoImpl
import com.kudagonish.data.local.RoomDataSource
import com.kudagonish.data.remote.RemoteRepoImpl
import com.kudagonish.data.remote.RetrofitImolementation
import com.kudagonish.languagedictionary.interactor.history.HistoryInteractor
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import com.kudagonish.history.HistoryViewModel
import com.kudagonish.languagedictionary.ui.main.MainViewModel
import com.kudagonish.models.*
import org.koin.dsl.module

val application = module {

    single { Room.databaseBuilder(get(), HistoryDatabase::class.java, "HistoryDB").build() }
    single { get<HistoryDatabase>().historyDao() }


    single<DataSource<List<DataModel>>>() {
        RetrofitImolementation()
    }
    single<Repository<List<DataModel>>> {
       RemoteRepoImpl(get())
    }


    single<DataSourceLocal<List<DataModel>>>() {
        RoomDataSource(get())
    }
    single<RepositoryLocal<List<DataModel>>> {
        LocalRepoImpl(get())
    }
}

val mainScreen = module {
    factory { MainInteractor(get(), get()) }
    factory { MainViewModel(get()) }
}
val historyScreen = module {
    factory<IHistoryInteractor> { HistoryInteractor(get()) }
    factory { com.kudagonish.history.HistoryViewModel(get()) }
}