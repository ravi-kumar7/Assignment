package com.android.assignment.di

import com.android.assignment.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(subcomponents = [])
abstract class ApplicationSubComponents{

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivityInjector(): MainActivity
}