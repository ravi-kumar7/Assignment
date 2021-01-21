package com.android.assignment.di.components

import com.android.assignment.MainActivity
import com.android.assignment.di.ActivityScope
import com.android.assignment.di.module.FragmentModule
import com.android.assignment.ui.main.MainFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module(subcomponents = [])
abstract class ApplicationSubComponents{

    @ActivityScope
    @ContributesAndroidInjector(modules = [])
    abstract fun contributeMainActivityInjector(): MainActivity

    @ActivityScope
    @ContributesAndroidInjector(modules = [FragmentModule::class])
    abstract fun contributeMainFragmentInjector(): MainFragment
}