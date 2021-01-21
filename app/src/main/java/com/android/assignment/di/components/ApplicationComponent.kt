package com.android.assignment.di.components

import com.android.assignment.MyApplication
import com.android.assignment.di.module.ApplicationModule
import com.android.assignment.di.module.DatabaseModule
import com.android.assignment.di.module.NetworkModule
import com.android.assignment.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class, ApplicationModule::class, DatabaseModule::class, ViewModelModule::class, NetworkModule::class
, ApplicationSubComponents::class])
interface ApplicationComponent: AndroidInjector<MyApplication> {

    @Component.Factory
    interface Factory: AndroidInjector.Factory<MyApplication>
}