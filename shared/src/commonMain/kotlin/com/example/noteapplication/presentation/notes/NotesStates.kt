package com.example.noteapplication.presentation.notes

import com.example.noteapplication.domain.model.note.NoteModel

sealed interface NotesStates {
    data class NotesState(
        val notesList: List<NoteModel> = emptyList(),
        val filteredNotes : List<NoteModel> = emptyList(),
        val searchText: String = "",
        val isSearchingActive: Boolean = false
    ) : NotesStates
}