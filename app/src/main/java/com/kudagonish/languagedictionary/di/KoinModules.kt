package com.kudagonish.languagedictionary.di

import androidx.room.Room
import com.kudagonish.languagedictionary.*
import com.kudagonish.languagedictionary.data.RemoteRepoImpl
import com.kudagonish.languagedictionary.data.local.HistoryDao
import com.kudagonish.languagedictionary.data.local.HistoryDatabase
import com.kudagonish.languagedictionary.data.local.LocalRepoImpl
import com.kudagonish.languagedictionary.data.local.RoomDataSource
import com.kudagonish.languagedictionary.data.remote.RetrofitImolementation
import com.kudagonish.languagedictionary.interactor.history.HistoryInteractor
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import com.kudagonish.languagedictionary.ui.history.HistoryViewModel
import com.kudagonish.languagedictionary.ui.main.MainViewModel
import org.koin.core.qualifier.named
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
    factory { HistoryViewModel(get()) }
}