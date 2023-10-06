package com.example.noteapplication.domain.repository

import com.example.noteapplication.data.dto.NoteDto
import com.example.noteapplication.domain.model.note.NoteModel
import kotlinx.coroutines.flow.Flow

interface Repository {

    suspend fun insertNote(note: NoteDto)
    suspend fun getNoteById(id : Long) : Flow<NoteModel?>
    suspend fun getAllNotes() : Flow<List<NoteModel>>
    suspend fun deleteNoteById(id :Long)

}