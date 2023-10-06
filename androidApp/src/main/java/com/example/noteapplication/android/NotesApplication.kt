package com.example.noteapplication.android

import android.app.Application
import com.example.noteapplication.di.initKoin
import com.example.noteapplication.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class NotesApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        initKoin {
            androidContext(this@NotesApplication)
            modules(
                listOf()
            )
        }
    }
}