package com.example.noteapplication.di

import com.example.noteapplication.data.local.DatabaseDriverFactory
import com.example.noteapplication.presentation.notes.NotesViewModel
import com.example.noteapplication.utils.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        DatabaseDriverFactory()
    }




    factory {
       NotesViewModel(get(),get(),get(),get(), ioDispatcher = get(named(Dispatchers.IO)))
    }

}

actual interface CommonParcelable