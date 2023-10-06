package com.example.noteapplication.di

import android.os.Parcelable
import com.example.noteapplication.data.local.DatabaseDriverFactory
import com.example.noteapplication.presentation.noteDetails.NoteDetailsViewModel
import com.example.noteapplication.presentation.notes.NotesViewModel
import com.example.noteapplication.utils.Dispatchers
import kotlinx.parcelize.Parcelize
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual typealias CommonParcelize = Parcelize

actual typealias CommonParcelable = Parcelable

actual fun platformModule() = module {

    single {
        DatabaseDriverFactory(androidContext()).createDriver()
    }



    viewModel {
        NotesViewModel(get(), get(), get(),ioDispatcher = get(named(Dispatchers.IO)))
    }

    viewModel {
        NoteDetailsViewModel(get(), get(), get(),ioDispatcher = get(named(Dispatchers.IO)))
    }
}