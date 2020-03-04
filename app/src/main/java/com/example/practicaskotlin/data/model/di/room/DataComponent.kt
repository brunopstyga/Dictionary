package com.example.practicaskotlin.data.model.di.room

import com.example.practicaskotlin.App
import com.example.practicaskotlin.data.model.MainActivityModule
import com.example.practicaskotlin.data.model.factory.ViewModelBuilder
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ViewModelBuilder::class,
        MainActivityModule::class
    ]
)

interface DataComponent : AndroidInjector<App>{

    @Component.Factory
    abstract class Factory : AndroidInjector.Factory<App?>

}