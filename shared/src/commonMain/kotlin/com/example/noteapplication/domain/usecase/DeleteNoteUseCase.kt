package com.example.noteapplication.domain.usecase

import com.example.noteapplication.domain.repository.Repository

class DeleteNoteUseCase(private val repository: Repository) {
    suspend fun execute(id: Long) {
        repository.deleteNoteById(id)
    }
}