package com.android.assignment.di.module

import android.content.Context
import com.android.assignment.MyApplication
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    fun provideContext(app: MyApplication): Context = app.applicationContext
}