package com.example.noteapplication.android.presentation.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.noteapplication.android.R
import com.example.noteapplication.android.presentation.components.HidableInputTextField
import com.example.noteapplication.android.presentation.components.NoteItem
import com.example.noteapplication.presentation.notes.NotesIntents
import com.example.noteapplication.presentation.notes.NotesViewModel
import org.koin.androidx.compose.getViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    viewModel: NotesViewModel = getViewModel(),
    navController: NavController
) {

    val state by viewModel.notesState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val notesListState = rememberLazyListState()


    LaunchedEffect(Unit) {
        viewModel
            .scrollUp
            .collect { scrollUp ->
                if (scrollUp) {
                    notesListState.animateScrollToItem(0)
                }
            }
    }

    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {
            navController.navigate(context.getString(R.string.details) + "/" + "-1")
        }, backgroundColor = Color.DarkGray) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "", tint = Color.White)
        }
    }) { paddingValues ->


        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {

                HidableInputTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(90.dp),
                    search = state.searchText,
                    isSearchActive = state.isSearchingActive,
                    onTextChange = {
                        viewModel.onEvent(NotesIntents.SearchNoteByTitle(it))
                    },
                    onSearchClicked = {
                        viewModel.onEvent(NotesIntents.ToggleSearch)
                    },
                    onCloseClicked = {
                        viewModel.onEvent(NotesIntents.ToggleSearch)
                    },

                    )
                this@Column.AnimatedVisibility(
                    visible = !state.isSearchingActive,
                    enter = fadeIn(),
                    exit = fadeOut(),
                    modifier = Modifier.align(Alignment.Center)
                ) {

                    Text(
                        text = stringResource(R.string.all_notes),
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )

                }
            }
            LazyColumn(modifier = Modifier.weight(1f), state = notesListState) {
                items(items = state.filteredNotes,
                    key = { it.id!! }
                ) { note ->
                    NoteItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .padding(16.dp)
                            .animateItemPlacement(),
                        note = note,
                        backgroundColor = Color(note.color),
                        onNoteClicked = {
                            navController.navigate(context.getString(R.string.details) + "/" + note.id)
                        },
                        onDeleteClicked = {
                            viewModel.onEvent(NotesIntents.DeleteNoteById(note.id!!))
                        })
                }

            }
        }


    }

}