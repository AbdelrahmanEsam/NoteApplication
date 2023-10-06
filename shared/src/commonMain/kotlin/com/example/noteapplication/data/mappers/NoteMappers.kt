package com.example.noteapplication.data.mappers

import com.example.noteapplication.data.dto.NoteDto
import com.example.noteapplication.database.NoteEntity
import com.example.noteapplication.domain.model.note.NoteModel
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime


fun NoteEntity.toNote(): NoteDto {
    return NoteDto(
        id = id,
        title = title,
        content = content,
        color = colorHex,
        timeStamp = Instant.fromEpochMilliseconds(createdAt).toLocalDateTime(
            TimeZone.currentSystemDefault()
        )
    )
}


fun NoteDto.toNoteModel(): NoteModel {
    return NoteModel(
        id = id,
        title = title,
        content = content,
        color = color,
        timeStamp = timeStamp
    )
}


fun NoteModel.toNoteDto(): NoteDto {
    return NoteDto(
        id = id,
        title = title,
        content = content,
        color = color,
        timeStamp = timeStamp
    )
}