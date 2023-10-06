package com.example.noteapplication.utils

import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel

actual abstract class ViewModel {
    actual  val viewModelScope = MainScope()

    protected actual  fun onCleared() {

    }

    fun clear() {
        onCleared()
        viewModelScope.cancel()
    }
}