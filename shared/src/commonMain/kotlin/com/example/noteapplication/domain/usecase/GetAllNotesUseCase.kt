package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class GetAllNotesUseCase(private val repository: Repository) {


    suspend fun execute(): Flow<List<NoteModel>> {
        return repository.getAllNotes()
    }
}