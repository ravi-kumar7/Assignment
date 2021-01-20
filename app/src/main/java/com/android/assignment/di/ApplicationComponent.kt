package com.android.assignment.di

import com.android.assignment.MyApplication
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,DatabaseModule::class,ViewModelModule::class,NetworkModule::class
,ApplicationSubComponents::class])
interface ApplicationComponent: AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication>
}