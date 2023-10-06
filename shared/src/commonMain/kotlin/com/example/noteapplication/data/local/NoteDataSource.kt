package com.example.noteapplication.data.local

import com.example.noteapplication.data.dto.NoteDto
import com.example.noteapplication.domain.model.note.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteDataSource {

    suspend fun insertNote(note: NoteDto)
    suspend fun getNoteById(id : Long) : Flow<NoteDto?>
    suspend fun getAllNotes() : Flow<List<NoteDto>>
    suspend fun deleteNoteById(id :Long)

}