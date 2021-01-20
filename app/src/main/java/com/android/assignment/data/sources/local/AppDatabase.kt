package com.android.assignment.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.assignment.data.model.Category
import com.android.assignment.data.model.Fact


@Database(entities = [Fact::class,Category::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun factDao(): FactDao

}