package com.android.assignment.data.repo.remote.api

import com.android.assignment.data.model.Response
import com.android.assignment.util.Constants
import kotlinx.coroutines.Deferred
import retrofit2.http.GET


interface NetworkService {

    @GET(Constants.END_POINT)
    fun fetchFactsAsync(): Deferred<Response>

}