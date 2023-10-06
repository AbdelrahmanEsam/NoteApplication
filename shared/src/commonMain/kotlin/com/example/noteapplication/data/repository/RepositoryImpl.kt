package com.example.noteapplication.data.repository

import com.example.noteapplication.data.dto.NoteDto
import com.example.noteapplication.data.local.NoteDataSource
import com.example.noteapplication.data.mappers.toNoteDto
import com.example.noteapplication.data.mappers.toNoteModel
import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.repository.Repository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RepositoryImpl(private val localDataSource: NoteDataSource) : Repository {

    override suspend fun insertNote(note: NoteDto) {
        Napier.d(tag = "Napier Error",message = note.toString())
        localDataSource.insertNote(note)
    }

    override suspend fun getNoteById(id: Long): Flow<NoteModel?> {
        return localDataSource.getNoteById(id).map { it?.toNoteModel() }
    }

    override suspend fun getAllNotes(): Flow<List<NoteModel>> {
        return localDataSource.getAllNotes().map { it.map { it.toNoteModel() } }
    }

    override suspend fun deleteNoteById(id: Long) {
        localDataSource.deleteNoteById(id)
    }
}