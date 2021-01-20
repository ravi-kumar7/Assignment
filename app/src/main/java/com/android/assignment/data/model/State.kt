package com.android.assignment.data.model

import androidx.annotation.StringRes

sealed class State{
    object Idle: State()
    object Loading: State()
    data class Success<T>(val data : T): State()
    data class Error(@StringRes val msg:Int): State()
}
