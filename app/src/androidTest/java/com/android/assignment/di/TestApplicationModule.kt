package com.android.assignment.di

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import com.android.assignment.MyApplication
import com.android.assignment.MyTestApplication
import dagger.Module
import dagger.Provides

@Module
class TestApplicationModule {

    @Provides
    fun provideContext(): Context = InstrumentationRegistry.getInstrumentation().targetContext
}