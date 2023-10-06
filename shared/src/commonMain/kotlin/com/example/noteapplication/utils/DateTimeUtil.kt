package com.example.noteapplication.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime


object DateTimeUtil {


    fun now(): LocalDateTime {
        return Clock.System.now().toLocalDateTime(TimeZone.currentSystemDefault())
    }


    fun toEpochTime(time: LocalDateTime): Long {
        return time.toInstant(TimeZone.currentSystemDefault()).toEpochMilliseconds()
    }

    fun formatNoteDate(time: LocalDateTime): String {

        val month = time.month.name.lowercase().take(3).replaceFirstChar { it.uppercase() }
        val day = if (time.dayOfMonth < 10) "0${time.dayOfMonth}" else time.dayOfMonth
        val year = time.year
        val hour = if (time.hour < 10) "0${time.hour}" else time.hour
        val min = if (time.minute < 10) "0${time.minute}" else time.minute

        return buildString {
            append(month)
            append(" ")
            append(day)
            append(" ")
            append(year)
            append(" ")
            append(hour)
            append(":")
            append(min)



        }
    }


}