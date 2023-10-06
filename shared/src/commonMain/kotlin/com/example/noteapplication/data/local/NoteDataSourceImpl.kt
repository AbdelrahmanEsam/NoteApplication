package com.example.noteapplication.data.local

import app.cash.sqldelight.coroutines.asFlow
import com.example.noteapplication.data.dto.NoteDto
import com.example.noteapplication.data.mappers.toNote
import com.example.noteapplication.database.Database
import com.example.noteapplication.utils.DateTimeUtil
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NoteDataSourceImpl(database: Database) : NoteDataSource {

    val queries = database.noteQueries

    override suspend fun insertNote(note: NoteDto) {
        with(note) {
            queries.insertNote(
                id = id,
                title = title,
                content = content,
                colorHex = color,
                createdAt = DateTimeUtil.toEpochTime(timeStamp)
            )
        }
    }

    override suspend fun getNoteById(id: Long): Flow<NoteDto?> {
        return queries.getNoteById(id).asFlow().map { it.executeAsOneOrNull()?.toNote() }
    }

    override suspend fun getAllNotes(): Flow<List<NoteDto>> {
        return queries.getAllNotes().asFlow().map { it.executeAsList().map { it.toNote() } }
    }

    override suspend fun deleteNoteById(id: Long) {
        queries.deleteNote(id)
    }
}