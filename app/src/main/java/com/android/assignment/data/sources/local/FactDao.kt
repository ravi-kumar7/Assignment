package com.android.assignment.data.sources.local

import androidx.room.*
import com.android.assignment.data.model.Category
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.Fact


@Dao
interface FactDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: Category)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFacts(facts:List<Fact>)

    @Transaction
    @Query("SELECT * FROM category limit 1")
    fun getCategoryWithFacts(): CategoryWithFacts?
}