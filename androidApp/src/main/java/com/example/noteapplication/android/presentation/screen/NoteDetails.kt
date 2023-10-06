package com.example.noteapplication.android.presentation.screen

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.noteapplication.android.presentation.components.TransparentTextField
import com.example.noteapplication.presentation.noteDetails.NoteDetailsIntents
import com.example.noteapplication.presentation.noteDetails.NoteDetailsViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun NoteDetails(
    modifier: Modifier = Modifier,
    viewModel: NoteDetailsViewModel = getViewModel(),
    navController: NavController,
    noteId: Long
) {

    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current


    if (noteId != -1L) {

        Log.d("noteId",noteId.toString())
        viewModel.onEvent(NoteDetailsIntents.GetNoteById(noteId))
    }

    LaunchedEffect(key1 = state.isNoteSaved) {
        if (state.isNoteSaved) {
            navController.popBackStack()
        }
    }


    LaunchedEffect(Unit) {
        viewModel
            .error
            .collect { message ->
                Toast.makeText(
                    context,
                    message,
                    Toast.LENGTH_SHORT,
                ).show()
            }
    }


    Scaffold(floatingActionButton = {
        FloatingActionButton(onClick = {

            viewModel.onEvent(NoteDetailsIntents.InsertNote)

        }, backgroundColor = Color.DarkGray) {
            Icon(imageVector = Icons.Default.Check, contentDescription = "", tint = Color.White)
        }
    }) { paddingValues ->


        Log.d("colorGenerated", state.noteColor.toString())
        Column(
            modifier = modifier
                .background(Color(state.noteColor))
                .fillMaxSize()
                .padding(paddingValues)
                .padding(top = 30.dp, start = 10.dp, end = 10.dp)
        ) {
            TransparentTextField(
                text = state.noteTitle,
                hint = "enter title",
                isHintVisible = state.isNoteHintTitleVisible,
                onFocusChanged = {
                    viewModel.onEvent(NoteDetailsIntents.OnTitleFocusChanged(focus = it.isFocused))
                },
                onValueChanged = {
                    viewModel.onEvent(NoteDetailsIntents.OnNewTitleChanged(it))
                },
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .height(60.dp)
            )


            TransparentTextField(
                text = state.noteContent,
                hint = "enter content",
                isHintVisible = state.isNoteContentHintVisible,
                onFocusChanged = {
                    viewModel.onEvent(NoteDetailsIntents.OnContentFocusChanged(focus = it.isFocused))
                },
                onValueChanged = {
                    viewModel.onEvent(NoteDetailsIntents.OnNewContentChanged(it))
                },
                singleLine = false,
                textStyle = TextStyle(fontSize = 20.sp),
                modifier = Modifier
                    .fillMaxHeight()
            )

        }


    }

}