package com.example.noteapplication.presentation.noteDetails

sealed interface NoteDetailsIntents {
    object InsertNote : NoteDetailsIntents
    data class GetNoteById(val id: Long) : NoteDetailsIntents
    data class OnTitleFocusChanged(val focus: Boolean) : NoteDetailsIntents
    data class OnContentFocusChanged(val focus: Boolean) : NoteDetailsIntents
    data class OnNewTitleChanged(val title: String) : NoteDetailsIntents
    data class OnNewContentChanged(val content: String) : NoteDetailsIntents

}