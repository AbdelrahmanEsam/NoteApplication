package com.example.noteapplication.domain.usecase

import com.example.noteapplication.data.mappers.toNoteDto
import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.repository.Repository

class InsertNoteUseCase(private val repository: Repository) {


    suspend fun execute(note : NoteModel) {
        repository.insertNote(note.toNoteDto())
    }
}