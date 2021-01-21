package com.android.assignment.data.sources.local

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.android.assignment.data.model.Category
import com.android.assignment.util.DummyData
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class AppDatabaseTest : TestCase(){

    private lateinit var factDao: FactDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        factDao = db.factDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }


    @Test
    @Throws(Exception::class)
    fun writeCategory() {
        val category = Category("DummyTitle")
        factDao.insertCategory(category)
        val result = factDao.getCategoryWithFacts()
        assertEquals(category.title,result?.category?.title)
    }

    @Test
    @Throws(Exception::class)
    fun writeFacts() {
        val category = Category("DummyTitle")
        factDao.insertCategory(category)
        val facts = DummyData.getData().filter { it.title.isNotBlank() }.map {
            it.category = category.title
            return@map it
        }
        factDao.insertFacts(facts)
        val result = factDao.getCategoryWithFacts()
        assertEquals(facts,result?.fact)
    }
}
