package com.android.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.Repository
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository,private val dispatcher: CoroutineDispatcher = Dispatchers.Main): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state

    private var synced = false
    private var loading = false


    fun getOrSyncData(){
        _state.value = State.Loading
        viewModelScope.launch(dispatcher){
            val data = repository.getCategoryWithFacts()
            if(data is State.Success<*>){
                _state.postValue(data)
                delay(2000)
            }
            syncData(true)
        }
    }

    fun syncData(forcedRefresh:Boolean=false)
    {
        if(synced && !forcedRefresh && _state.value is State.Loading)
            return
        _state.postValue(State.Loading)
        viewModelScope.launch(dispatcher){
                val res =  repository.syncDB()
                if(res is State.Success<*>) {
                    synced = true
                }
                loading = false
                _state.postValue(res)
            }
    }

}