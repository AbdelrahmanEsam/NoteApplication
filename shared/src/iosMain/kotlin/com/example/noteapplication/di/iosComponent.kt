package com.example.noteapplication.di

import com.example.noteapplication.data.local.DatabaseDriverFactory
import com.example.noteapplication.presentation.notes.NotesViewModel
import com.example.noteapplication.utils.Dispatchers
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ObjCClass
import kotlinx.cinterop.getOriginalKotlinClass
import org.koin.core.Koin
import org.koin.core.KoinApplication
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual fun platformModule() = module {
    single {
        DatabaseDriverFactory()
    }




    factory {
       NotesViewModel(get(),get(),get(), ioDispatcher = get(named(Dispatchers.IO)))
    }

}

object KoinIOS {
    fun initialize(): KoinApplication = initKoin()
}

@OptIn(BetaInteropApi::class)
fun Koin.get(objCClass: ObjCClass): Any {
    val kClass = getOriginalKotlinClass(objCClass)!!
    return get(kClass, qualifier = null, parameters = null )
}

actual interface CommonParcelable