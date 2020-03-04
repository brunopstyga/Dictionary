package com.example.practicaskotlin.data.model

import com.example.practicaskotlin.presentation.view.FragmentEdit
import com.example.practicaskotlin.presentation.view.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector
    abstract fun bindSearchFragment(): FragmentEdit

    @ContributesAndroidInjector
    abstract fun bindResultsFragment(): ListFragment

//    @ContributesAndroidInjector
//    abstract fun bindDetailFragment(): DetailFragment

}