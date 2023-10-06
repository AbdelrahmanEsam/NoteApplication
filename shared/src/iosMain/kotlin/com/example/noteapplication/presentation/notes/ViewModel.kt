package com.example.noteapplication.presentation.notes

import com.example.noteapplication.utils.ViewModel
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.Flow


abstract class CallbackViewModel {
    protected abstract val viewModel: ViewModel


    fun <T : Any> Flow<T>.asCallbacks() =
        FlowAdapter(viewModel.viewModelScope, this)

    @Suppress("Unused") // Called from Swift
    fun clear() = viewModel.clear()
}