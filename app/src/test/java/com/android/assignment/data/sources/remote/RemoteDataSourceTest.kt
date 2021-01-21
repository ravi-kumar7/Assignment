package com.android.assignment.data.sources.remote

import com.android.assignment.R
import com.android.assignment.data.model.Response
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.remote.api.NetworkService
import com.android.assignment.util.DummyData
import junit.framework.TestCase
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteDataSourceTest : TestCase() {

    @Mock
    private lateinit var networkService: NetworkService

    private lateinit var remoteDataSource: RemoteDataSource

    @Before
    fun setup(){
        networkService = mock(NetworkService::class.java)
        remoteDataSource = RemoteDataSource(networkService)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetDataFromAPI_Success() {
        runBlockingTest {
            val facts  = DummyData.getData()
            val response = Response(DummyData.getTitle(), facts )
            val deferred =  CompletableDeferred<Response>()
            `when`(networkService.fetchFactsAsync()).thenReturn(deferred)
            deferred.complete(response)
            val outState = remoteDataSource.getDataFromAPI()
            assert(outState == State.Success(response))
        }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetDataFromAPI_Exception() {
        runBlockingTest {
            `when`(networkService.fetchFactsAsync()).thenThrow(RuntimeException("Test"))
            val outState = remoteDataSource.getDataFromAPI()
            assert(outState == State.Error(R.string.error))
        }
    }
}