package com.android.assignment.data.sources.remote

import android.util.Log
import com.android.assignment.R
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.remote.api.NetworkService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val networkService: NetworkService) {

    suspend fun getDataFromAPI(): State {
        return try {
            State.Success(networkService.fetchFactsAsync().await())
        }
        catch (e:Exception){
            Log.d("Test",e.message!!)
            State.Error(R.string.error)
        }
    }
}