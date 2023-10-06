package com.example.noteapplication.presentation.noteDetails

import com.example.noteapplication.domain.model.note.NoteModel

sealed interface NoteDetailsStates {

    data class NoteDetailState(
        val noteTitle : String = "",
        val isNoteHintTitleVisible : Boolean = true,
        val noteContent : String = "",
        val isNoteContentHintVisible : Boolean = true,
        val noteColor : Long = NoteModel.generateRandomColor(),
        val isNoteSaved : Boolean  = false,
    )

}