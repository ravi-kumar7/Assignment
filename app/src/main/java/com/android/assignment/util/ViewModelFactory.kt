package com.android.assignment.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val viewModelMap: Map<Class<out ViewModel>,ViewModel> ):
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelMap[modelClass] as T
    }
}