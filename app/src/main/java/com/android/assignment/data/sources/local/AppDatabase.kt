package com.android.assignment.data.sources.local

import androidx.room.Database
import com.android.assignment.data.model.Category
import com.android.assignment.data.model.Fact


@Database(entities = [Fact::class,Category::class], version = 1)
abstract class AppDatabase {

    abstract fun factDao(): FactDao

}