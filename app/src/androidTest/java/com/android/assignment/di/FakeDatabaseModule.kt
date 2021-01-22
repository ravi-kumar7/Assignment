package com.android.assignment.di

import com.android.assignment.data.model.Category
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.Fact
import com.android.assignment.data.repo.local.FactDao
import com.android.assignment.util.DummyData
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class FakeDatabaseModule {

    @Singleton
    @Provides
    fun provideFactDao(): FactDao {
        val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
        return object : FactDao {
            override fun insertCategory(category: Category) {

            }

            override fun insertFacts(facts: List<Fact>) {

            }

            override fun getCategoryWithFacts(): CategoryWithFacts? {
                return categoryWithFacts
            }
        }
    }
}