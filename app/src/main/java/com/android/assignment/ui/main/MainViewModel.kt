package com.android.assignment.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.Repository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: Repository): ViewModel() {

    private val _state: MutableLiveData<State> = MutableLiveData(State.Idle)
    val state: LiveData<State> = _state

    fun getOrSyncData(){
        _state.value = State.Loading
        viewModelScope.launch(){
            var data = repository.getCategoryWithFacts()
            if(data is State.Error){
                data = repository.syncDB()
            }
            _state.postValue(data)
        }
    }

    fun syncData()
    {
        _state.value = State.Loading
            viewModelScope.launch(){
                _state.postValue(repository.syncDB())
            }
    }
}