package com.example.noteapplication.utils

import kotlinx.coroutines.CoroutineScope

expect abstract class ViewModel() {
      val viewModelScope: CoroutineScope

    protected open fun onCleared()
}