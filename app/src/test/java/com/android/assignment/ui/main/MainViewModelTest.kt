package com.android.assignment.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.android.assignment.data.model.Category
import com.android.assignment.data.model.CategoryWithFacts
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.Repository
import com.android.assignment.util.DummyData
import junit.framework.TestCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.*
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MainViewModelTest : TestCase(){

    @get:Rule
    val rule = InstantTaskExecutorRule()

    lateinit var viewModel: MainViewModel
    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    lateinit var spyViewModel: MainViewModel

    @Mock
    private lateinit var repository: Repository

    @Mock
    private lateinit var stateObserver: Observer<State>

    @Before
    fun setup(){
        Dispatchers.setMain(testCoroutineDispatcher)
        repository = mock(Repository::class.java)
        viewModel = MainViewModel(repository)
        spyViewModel = spy(viewModel)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun testGetOrSyncData() {
        testCoroutineDispatcher.runBlockingTest {
            val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
            doReturn(State.Success(categoryWithFacts)).`when`(repository).getCategoryWithFacts()
            viewModel.getOrSyncData()
            assert(viewModel.state.value == State.Success(categoryWithFacts))
        }
    }

    @Test
    fun testSyncData() {
        testCoroutineDispatcher.runBlockingTest {
            val categoryWithFacts = CategoryWithFacts(Category("Dummy Title"), DummyData.getData())
            doReturn(State.Success(categoryWithFacts)).`when`(repository).syncDB()
            viewModel.syncData()
            assert(viewModel.state.value == State.Success(categoryWithFacts))
        }
    }

    @After
    fun classTearDown(){
        Dispatchers.resetMain()
        testCoroutineDispatcher.cleanupTestCoroutines()
    }
}