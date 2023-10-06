package com.example.noteapplication.domain.model.note

import com.example.noteapplication.presentation.BabyBlueHex
import com.example.noteapplication.presentation.LightGreenHex
import com.example.noteapplication.presentation.RedOrangeHex
import com.example.noteapplication.presentation.RedPinkHex
import com.example.noteapplication.presentation.VioletHex
import kotlinx.datetime.LocalDateTime

data class NoteModel(
    val id: Long? = null,
    val title: String,
    val content: String,
    val color: Long,
    val timeStamp: LocalDateTime
){
    companion object{
        private val colors =  listOf(RedOrangeHex, RedPinkHex, VioletHex,
            BabyBlueHex, LightGreenHex)

        fun generateRandomColor() = colors.random()
    }
}
