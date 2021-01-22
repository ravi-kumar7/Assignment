package com.android.assignment.di

import com.android.assignment.data.model.Response
import com.android.assignment.data.repo.remote.api.NetworkService
import com.android.assignment.util.DummyData
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import javax.inject.Singleton

@Module
class FakeNetworkModule {

    @Singleton
    @Provides
    fun provideNetworkService(): NetworkService {
        val facts  = DummyData.getData()
        val response = Response(DummyData.getTitle(), facts )


        return object: NetworkService{
            override fun fetchFactsAsync(): Deferred<Response> {
                return CompletableDeferred(response)
            }
        }
    }


}