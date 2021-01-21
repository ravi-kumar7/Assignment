package com.android.assignment.data.sources

import com.android.assignment.R
import com.android.assignment.data.model.Category
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.Response
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.local.LocalDataSource
import com.android.assignment.data.sources.remote.RemoteDataSource
import com.android.assignment.data.sources.remote.api.NetworkService
import com.android.assignment.util.DummyData
import com.android.assignment.util.NetworkHelper
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RepositoryTest : TestCase() {

    @Mock
    private lateinit var remoteDataSource: RemoteDataSource
    @Mock
    private lateinit var localDataSource: LocalDataSource
    @Mock
    private lateinit var networkHelper: NetworkHelper

    private lateinit var repository: Repository

    @Before
    fun setup(){
        remoteDataSource = mock(remoteDataSource::class.java)
        localDataSource = mock(localDataSource::class.java)
        networkHelper = mock(NetworkHelper::class.java)
        repository = Repository(networkHelper,localDataSource,remoteDataSource )
    }

    @Test
    fun testGetCategoryWithFacts() {
        val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
        doReturn(State.Success(categoryWithFacts)).`when`(localDataSource).getCategoryWithFacts()
        val data = repository.getCategoryWithFacts()
        assertEquals(State.Success(categoryWithFacts),data)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSyncDB_NoInternet() {
        runBlockingTest {
        doReturn(false).`when`(networkHelper).checkNetworkAvailability()
        val data = repository.syncDB()
        assertEquals(State.Error(R.string.no_internet),data)
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testSyncDB_InternetAvailable_SuccessData() {
        runBlockingTest {
            val facts = DummyData.getData()
            val response = Response(DummyData.getTitle(), facts)
            val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
            doReturn(State.Success(categoryWithFacts)).`when`(localDataSource).getCategoryWithFacts()
            doReturn(State.Success(response)).`when`(remoteDataSource).getDataFromAPI()
            doReturn(true).`when`(networkHelper).checkNetworkAvailability()
            val data = repository.syncDB()
            assertEquals(data, State.Success(categoryWithFacts))
        }
    }


    @ExperimentalCoroutinesApi
    @Test
    fun testSyncDB_InternetAvailable_Error() {
        runBlockingTest {
            doReturn(State.Error(-1)).`when`(remoteDataSource).getDataFromAPI()
            doReturn(true).`when`(networkHelper).checkNetworkAvailability()
            val data = repository.syncDB()
            assertEquals(data,State.Error(-1))
        }
    }
}