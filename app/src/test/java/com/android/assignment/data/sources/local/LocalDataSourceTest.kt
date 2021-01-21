package com.android.assignment.data.sources.local

import com.android.assignment.data.model.Category
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.Response
import com.android.assignment.data.model.State
import com.android.assignment.util.DummyData
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class LocalDataSourceTest : TestCase() {

    @Mock
    private lateinit var factDao: FactDao

    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup(){
        factDao = mock(FactDao::class.java)
        localDataSource = LocalDataSource(factDao)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSaveData() {
            val facts  = DummyData.getData()
            val response = Response(DummyData.getTitle(), facts )
            localDataSource.saveData(response)
            verify(factDao).insertCategory(Category("DummyTitle"))
            verify(factDao).insertFacts(facts.filter { !it.title.isNullOrBlank() }.map {
                it.category = DummyData.getTitle()
                return@map it
            })
    }

    @Test
    fun testGetCategoryWithFacts_whenDataExists_returnData() {
        val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
        Mockito.doReturn(categoryWithFacts).`when`(factDao).getCategoryWithFacts()
        val resultState = localDataSource.getCategoryWithFacts()
        assertEquals(resultState,State.Success(categoryWithFacts))
    }

    @Test
    fun testGetCategoryWithFacts_whenDataDoesNotExists_returnError() {
        CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
        Mockito.doReturn(null).`when`(factDao).getCategoryWithFacts()
        val resultState = localDataSource.getCategoryWithFacts()
        assertEquals(resultState,State.Error(-1))
    }
}