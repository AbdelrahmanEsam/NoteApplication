package com.example.noteapplication.data.dto

import com.example.noteapplication.presentation.LightGreenHex
import com.example.noteapplication.presentation.RedOrangeHex
import com.example.noteapplication.presentation.RedPinkHex
import com.example.noteapplication.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class NoteDto(
    val id: Long?,
    val title: String,
    val content: String,
    val color: Long,
    val timeStamp: LocalDateTime
){
    companion object{
        private val colors =  listOf(RedOrangeHex, RedPinkHex, VioletHex, LightGreenHex)

        fun generateRandomColor() = colors.random()
    }
}
