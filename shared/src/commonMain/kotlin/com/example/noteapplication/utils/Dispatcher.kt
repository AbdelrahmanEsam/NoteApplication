package com.example.noteapplication.utils
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.annotation.AnnotationTarget.CONSTRUCTOR

@Target(FIELD,CONSTRUCTOR)
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: Dispatchers)

enum class Dispatchers {
    Default,
    IO,
    Main,
    Unconfined
}
