package com.example.noteapplication.di

import com.example.noteapplication.data.local.NoteDataSource
import com.example.noteapplication.data.local.NoteDataSourceImpl
import com.example.noteapplication.data.repository.RepositoryImpl
import com.example.noteapplication.database.Database
import com.example.noteapplication.domain.repository.Repository
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetAllNotesUseCase
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
import com.example.noteapplication.domain.usecase.InsertNoteUseCase
import com.example.noteapplication.utils.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module


fun initKoin(
    appDeclaration: KoinAppDeclaration = {}
) =
    startKoin {
        modules(commonModule())
        appDeclaration()
    }

fun initKoin() = initKoin {

}

fun commonModule() = platformModule() + commonMainModule()


fun commonMainModule() = module {


    single {
        Database(get())
    }

    single<NoteDataSource> {
        NoteDataSourceImpl(
            get()
        )
    }

    single<Repository> {
        RepositoryImpl(
            get()
        )
    }



    single {
        DeleteNoteUseCase(get())
    }

    single {
        InsertNoteUseCase(get())
    }

    single {
        GetAllNotesUseCase(get())
    }

    single {
        GetNoteByIdUseCase(get())
    }

    single(named(Dispatchers.IO)) {
        kotlinx.coroutines.Dispatchers.IO
    }

}