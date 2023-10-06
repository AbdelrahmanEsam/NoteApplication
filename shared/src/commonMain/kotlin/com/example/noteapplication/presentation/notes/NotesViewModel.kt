package com.example.noteapplication.presentation.notes

import com.example.noteapplication.domain.model.note.NoteModel
import com.example.noteapplication.domain.usecase.DeleteNoteUseCase
import com.example.noteapplication.domain.usecase.GetAllNotesUseCase
import com.example.noteapplication.domain.usecase.InsertNoteUseCase
import com.example.noteapplication.utils.DateTimeUtil
import com.example.noteapplication.utils.ViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch


class NotesViewModel(
    private val getAllNotesUseCase: GetAllNotesUseCase,
    private val insertNoteUseCase: InsertNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val ioDispatcher: CoroutineDispatcher,
) : ViewModel() {

    private val _notesState: MutableStateFlow<NotesStates.NotesState> =
        MutableStateFlow(NotesStates.NotesState())
    val notesState = _notesState.asStateFlow()

    private val _scrollUp: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val scrollUp = _scrollUp.asSharedFlow()


    fun onEvent(intent: NotesIntents) {
        when (intent) {
            is NotesIntents.DeleteNoteById -> {
                deleteNoteById(intent.id)
            }

            NotesIntents.GetAllNotes -> {
                getAllNotes()
            }


            is NotesIntents.SearchNoteByTitle -> {
                searchNoteByTitle(intent.search)
            }

            NotesIntents.ToggleSearch -> {
                toggleSearch()
            }
        }
    }


    private fun getAllNotes() {
        viewModelScope.launch(ioDispatcher) {
            getAllNotesUseCase.execute().collectLatest { notes ->
                _notesState.update { it.copy(notesList = notes , filteredNotes =  notes) }
            }
        }
    }

    private fun searchNoteByTitle(search: String) {
        _notesState.update { it.copy(searchText = search) }
        viewModelScope.launch(ioDispatcher) {
            _notesState.update {
                it.copy(filteredNotes = it.notesList.filter { note ->
                    note.title.lowercase().trim().contains(search) || note.content.lowercase()
                        .trim()
                        .contains(search)
                })
            }
            _scrollUp.emit(true)
        }
    }


    private fun deleteNoteById(id: Long) {
        viewModelScope.launch(ioDispatcher) {
            deleteNoteUseCase.execute(id)
        }
    }


    private fun toggleSearch() {
        _notesState.update { it.copy(isSearchingActive = !it.isSearchingActive) }
        if (!_notesState.value.isSearchingActive) {
            _notesState.update { it.copy(searchText = "") }
        }
    }

    override fun onCleared() {

    }

    init {
        onEvent(NotesIntents.GetAllNotes)
    }

}