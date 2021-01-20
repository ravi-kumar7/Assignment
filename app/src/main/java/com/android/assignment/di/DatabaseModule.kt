package com.android.assignment.di

import android.content.Context
import androidx.room.Room
import com.android.assignment.data.sources.local.AppDatabase
import com.android.assignment.data.sources.local.FactDao
import com.android.assignment.util.Constants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDataBase(context: Context): AppDatabase = Room.databaseBuilder(context,AppDatabase::class.java,Constants.DB_NAME)
            .build()

    @Singleton
    @Provides
    fun provideFactDao(appDatabase: AppDatabase): FactDao = appDatabase.factDao()

}