package com.android.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val repository: Repository): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state

    fun getOrSyncData(){
        _state.value = State.Loading
        viewModelScope.launch(Dispatchers.IO){
            val data = repository.getCategoryWithFacts()
            if(data is State.Success<*>){
                _state.postValue(data)
            }
            syncData()
        }
    }

    fun syncData()
    {
        _state.value = State.Loading
            viewModelScope.launch(Dispatchers.IO){
                _state.postValue(repository.syncDB())
            }
    }
}