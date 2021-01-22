package com.android.assignment.di

import com.android.assignment.di.components.ApplicationComponent
import com.android.assignment.di.components.ApplicationSubComponents
import com.android.assignment.di.module.DatabaseModule
import com.android.assignment.di.module.FragmentModule
import com.android.assignment.di.module.NetworkModule
import com.android.assignment.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class,TestApplicationModule::class,ViewModelModule::class,FragmentModule::class,ApplicationSubComponents::class
,FakeDatabaseModule::class, FakeNetworkModule::class])
interface TestAppComponent: ApplicationComponent{

}