package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetNoteByIdUseCase(private val repository: Repository) {


    suspend fun execute(id : Long): Flow<NoteModel?> {
        return repository.getNoteById(id)
    }
}