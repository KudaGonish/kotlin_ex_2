package com.kudagonish.languagedictionary.ui.main

import com.kudagonish.languagedictionary.AppSate
import com.kudagonish.languagedictionary.Interactor
import com.kudagonish.languagedictionary.Presenter
import com.kudagonish.languagedictionary.View
import com.kudagonish.languagedictionary.data.RepoImpl
import com.kudagonish.languagedictionary.data.remote.DataSourceRemote
import com.kudagonish.languagedictionary.interactor.main.MainInteractor
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.RunnableScheduledFuture

class MainPresenter<T: AppSate, V: View>(
    private val interactor: MainInteractor = MainInteractor(
        RepoImpl(DataSourceRemote()),
        RepoImpl(DataSourceRemote()),
    )
) : Presenter<T, V>{
    private val compositeDisposable = CompositeDisposable()

    private var currentView: V? = null
    override fun attachView(view: V) {
        if(currentView != view){
            currentView = view
        }

    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if(view == currentView){
            currentView == null
        }
    }

    override fun getData(word: String, isOnline: Boolean) {
        compositeDisposable.add( interactor.getData(word, isOnline).subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ currentView?.renderData(AppSate.Loading(null))}
            .subscribe{currentView?.renderData(it)})
    }


}