package com.android.assignment.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.android.assignment.data.sources.Repository
import com.android.assignment.di.ViewModelKey
import com.android.assignment.ui.main.MainViewModel
import com.android.assignment.util.ViewModelFactory

import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
class ViewModelModule {

    @Provides
    fun getViewModelFactory(map:Map<Class<out ViewModel>, ViewModel>): ViewModelProvider.Factory {
        return ViewModelFactory(map)
    }


    @Provides
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun provideMainViewModel(repository: Repository): MainViewModel =
            MainViewModel(repository)

}