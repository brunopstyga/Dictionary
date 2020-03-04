package com.example.practicaskotlin


import com.example.practicaskotlin.data.model.di.room.DaggerDataComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class App : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<App?>? = DaggerDataComponent.factory().create(this)

}