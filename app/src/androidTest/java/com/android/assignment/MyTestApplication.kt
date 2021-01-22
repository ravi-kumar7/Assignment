package com.android.assignment

import com.android.assignment.di.DaggerTestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class MyTestApplication: MyApplication(){
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>
     = DaggerTestAppComponent.create()
}