package com.example.practicaskotlin.data.model.di.room

import android.app.Application
import com.example.practicaskotlin.App
import dagger.Binds
import dagger.Module

@Module(includes = [
DbModule::class
])

abstract class AppModule {
    @Binds
    abstract fun bindApplication(app: App?): Application?
}