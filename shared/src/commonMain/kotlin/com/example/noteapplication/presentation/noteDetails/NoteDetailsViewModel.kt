package com.example.noteapplication.presentation.noteDetails

import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.usecase.GetAllNotesUseCase
import com.example.noteapplication.domain.usecase.GetNoteByIdUseCase
import com.example.noteapplication.domain.usecase.InsertNoteUseCase
import com.example.noteapplication.utils.DateTimeUtil
import com.example.noteapplication.utils.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteDetailsViewModel(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _state = MutableStateFlow(NoteDetailsStates.NoteDetailState())
    val state = _state.asStateFlow()

    private val _error: MutableSharedFlow<String> = MutableSharedFlow()
    val error = _error.asSharedFlow()


    fun onEvent(intent: NoteDetailsIntents) {
        when (intent) {
            is NoteDetailsIntents.GetNoteById -> {
                getNoteById(intent.id)
            }

            is NoteDetailsIntents.InsertNote -> {
                insertNoteById()
            }

            is NoteDetailsIntents.OnContentFocusChanged -> {
                _state.update { it.copy(isNoteContentHintVisible = !intent.focus && _state.value.noteContent.isEmpty()) }
            }

            is NoteDetailsIntents.OnNewContentChanged -> {
                _state.update { it.copy(noteContent = intent.content) }
            }

            is NoteDetailsIntents.OnNewTitleChanged -> {
                _state.update { it.copy(noteTitle = intent.title) }
            }

            is NoteDetailsIntents.OnTitleFocusChanged -> {
                _state.update { it.copy(isNoteHintTitleVisible = !intent.focus && _state.value.noteTitle.isEmpty()) }
            }
        }

    }


    private fun insertNoteById() {

        with(_state.value) {

            viewModelScope.launch(ioDispatcher) {

                if (noteTitle.isEmpty() || noteContent.isEmpty()) {
                    _error.emit("please add title and content")
                    return@launch
                } else {


                    insertNoteUseCase.execute(
                        NoteModel(
                            title = noteTitle,
                            content = noteContent,
                            color = NoteModel.generateRandomColor(),
                            timeStamp = DateTimeUtil.now()
                        )
                    )
                _state.update { it.copy(isNoteSaved = true) }
                }
            }
        }
    }


    private fun getNoteById(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            getNoteByIdUseCase.execute(id).collectLatest { note ->
                _state.update {
                    it.copy(
                        noteTitle = note?.title ?: "",
                        noteContent = note?.content ?: "",
                        noteColor = note?.color ?: 0xFFFFFF
                    )
                }
            }
        }
    }



}