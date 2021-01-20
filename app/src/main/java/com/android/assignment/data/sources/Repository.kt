package com.android.assignment.data.sources

import com.android.assignment.R
import com.android.assignment.data.model.Response
import com.android.assignment.data.model.State
import com.android.assignment.data.sources.local.LocalDataSource
import com.android.assignment.data.sources.remote.RemoteDataSource
import com.android.assignment.util.NetworkHelper
import javax.inject.Singleton

@Singleton
class Repository(private val networkHelper: NetworkHelper,private val localDataSource: LocalDataSource, private val remoteDataSource: RemoteDataSource){

    fun getCategoryWithFacts(): State {
        return localDataSource.getCategoryWithFacts()
    }

    suspend fun syncDB():State{
        if(!networkHelper.checkNetworkAvailability())
            return State.Error(R.string.no_internet)
        val res = remoteDataSource.getDataFromAPI()
        if(res is State.Error)
            return res
        val data = (res as State.Success<*>).data as Response
        localDataSource.saveData(data)
        return State.Success(localDataSource.getCategoryWithFacts())
    }

}