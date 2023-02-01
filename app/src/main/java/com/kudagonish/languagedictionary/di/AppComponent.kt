package com.kudagonish.languagedictionary.di

import android.app.Application
import com.kudagonish.languagedictionary.TranslatorApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Component(
    modules = [
        InteractorModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepositoryModule::class,
        AndroidSupportInjectionModule::class
    ]
)
@Singleton
interface AppComponent {
 @Component.Builder
 interface Builder{
     @BindsInstance
     fun application(app: Application): Builder

     fun build(): AppComponent
 }
    fun inject(app: TranslatorApp)
}