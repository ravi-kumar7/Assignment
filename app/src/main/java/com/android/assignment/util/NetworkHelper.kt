package com.android.assignment.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities


class NetworkHelper(private val context: Context){

    fun checkNetworkAvailability(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.activeNetwork?.let { networkInfo ->
            connectivityManager.getNetworkCapabilities(networkInfo)?.let {
                return it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || it.hasTransport(
                        NetworkCapabilities.TRANSPORT_WIFI)
                }
            }
        return false
    }
}